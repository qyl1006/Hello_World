package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.mapper.StockOutComeBillMapper;
import cn.wolfcode.wms.mapper.StockOutComebillItemMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutComeBillQueryObject;
import cn.wolfcode.wms.service.lStockOutComeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockOutComeBillServiceImpl implements lStockOutComeBillService {
    @Autowired
    private StockOutComeBillMapper stockOutComeBill;
    //明细
    @Autowired
    private StockOutComebillItemMapper stockOutComebillItemMapper;
    //库存
    @Autowired
    private ProductStockMapper productStockMapper;
    //销售账单
    @Autowired
    private SaleAccountMapper saleAccountMapper;

    @Override
    public void insertOrUpdate(StockOutComeBill entity) {
        if (entity.getId() == null) {
            saveBill(entity);
        }else{
            updateBill(entity);
        }
    }


    //订单更新
    private void updateBill(StockOutComeBill entity) {
        //根据订单的ID查询完整的信息
        StockOutComeBill old = stockOutComeBill.selectCheckStatusById(entity.getId());

        //检查订单状态 未审核才能更新
        if (StockOutComeBill.NORMAL.equals(old.getStatus())) {
            //设置明细数据
            //定义变量 记录订单的总数量 总金额
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            //更新前删除当前订单中所有的明细
            stockOutComebillItemMapper.deleteByPrimaryKey(entity.getId());

            for (StockOutComebillItem item : entity.getItems()) {
                //计算明细中的小计
                BigDecimal costPrice = item.getSalePrice();
                BigDecimal number = item.getNumber();

                BigDecimal amount = costPrice.multiply(number).setScale(2,RoundingMode.HALF_UP);
                item.setAmount(amount);

                //累加订单中的总数量 总金额
                totalAmount = totalAmount.add(amount);
                totalNumber = totalNumber.add(number);

                //保存明细 有订单ID
                item.setBillId(old.getId());
                stockOutComebillItemMapper.insert(item);

            }
            //设置订单
            entity.setTotalNumber(totalNumber);
            entity.setTotalAmount(totalAmount);

            stockOutComeBill.updateByPrimaryKey(entity);
        }
    }


    //订单新增
    private void saveBill(StockOutComeBill entity) {
        //设置录入人
        entity.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        entity.setInputTime(new Date());
        //设置单据状态
        entity.setStatus(StockOutComeBill.NORMAL);

        //设置计算明细数据
        //定义变量 来接收当前订单的商品总数量和总价格
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockOutComebillItem item : entity.getItems()) {
            //计算明细中的小计
            BigDecimal costPrice = item.getSalePrice();
            BigDecimal number = item.getNumber();

            BigDecimal amount = costPrice.multiply(number).setScale(2, RoundingMode.HALF_UP);
            item.setAmount(amount);

            //累加订单的总数量 总金额
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }

        //先保存订单 这样订单才有ID  明细才能完整的存入数据库
        //设置当前订单的商品总数量  总金额
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        stockOutComeBill.insert(entity);

        //保存明细
        for (StockOutComebillItem item : entity.getItems()) {
            item.setBillId(entity.getId());

            stockOutComebillItemMapper.insert(item);
        }
    }

    @Override
    public void deleteById(Long id) {
        //先删除当前订单下的所有明细
        stockOutComebillItemMapper.deleteByPrimaryKey(id);

        stockOutComeBill.deleteByPrimaryKey(id);
    }

    @Override
    public StockOutComeBill getById(Long id) {
        return stockOutComeBill.selectByPrimaryKey(id);
    }



    //分页
    @Override
    public PageResult queryAll(StockOutComeBillQueryObject qo) {
        Integer count = stockOutComeBill.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutComeBill> data = stockOutComeBill.selectAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
    
    //审核
    @Override
    public void updataAuditorById(Long id) {
        //根据单据的ID查询完整信息
        StockOutComeBill bill = stockOutComeBill.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能审核
        if(StockOutComeBill.NORMAL.equals(bill.getStatus())){


            //获取当前单据的货物仓库对象
            Depot depot = bill.getDepot();

            //迭代当前单据中所有的明细 依次出仓库
            for (StockOutComebillItem item : bill.getItems()) {
                //获取单据货品对象
                Product p = item.getProduct();

                //出库操作
                //查询当前商品所入库的仓库中是否拥有该商品的库存
                ProductStock ps = productStockMapper.selectByProductAndDepot(p.getId(), depot.getId());
                //1 该商品在该仓库中没有库存
                if (ps == null) {
                    String msg = "仓库:[" + depot.getName() + "]没有[" + p.getName() + "]商品的库存";
                    throw new RuntimeException(msg);
                }
                // 2 检查仓库数量是否满足出库的数量
                if (item.getNumber().compareTo(ps.getStoreNumber()) > 0){
                    String msg = "仓库:[" + depot.getName() + p.getName() + "]商品的仓库为:"
                            + ps.getStoreNumber() + "不足出库数量:"+ item.getNumber();

                    throw new RuntimeException(msg);
                }

                //该货物的销售数量
                BigDecimal emtpNum = item.getNumber();

                // 3 满足出库要求 减少库存总价值() 总数量
                //库存货物销售后的数量
                BigDecimal costNum = ps.getStoreNumber().subtract(emtpNum);
                //库存货物销售后的总价值
                BigDecimal costAmount = costNum.multiply(ps.getPrice()).setScale(2, RoundingMode.HALF_UP);
                ps.setStoreNumber(costNum);
                ps.setAmount(costAmount);
                //更新仓库信息
                productStockMapper.updateByPrimaryKey(ps);

                //记录销售账单信息
                SaleAccount account = new SaleAccount();
                //时间
                account.setVdate(new Date());

                //数量
                account.setNumber(emtpNum);
                //成本价
                account.setCostPrice(ps.getPrice());
                //销售成本价总额
                account.setCostAmount(emtpNum.multiply(ps.getPrice()).setScale(2,RoundingMode.HALF_UP));
                //销售价
                account.setSalePrice(item.getSalePrice());
                //销售总额
                account.setSaleAmount(emtpNum.multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));

                //货物
                account.setProduct(ps.getProduct());
                //销售人
                account.setSaleman(UserContext.getCurrentUser());
                //客户
                account.setClient(bill.getClient());

                //保存销售账单到数据库
                saleAccountMapper.insert(account);

            }
        }

        //设置单据的审核人 时间 状态
        bill.setAuditTime(new Date());
        bill.setAuditor(UserContext.getCurrentUser());
        bill.setStatus(StockOutComeBill.AUDIT);
        stockOutComeBill.updateAuditorById(bill);
    }
}
