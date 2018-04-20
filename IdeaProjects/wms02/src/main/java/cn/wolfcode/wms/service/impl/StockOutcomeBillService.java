package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockOutcomeBillService implements IStockOutcomeBillService {
    @Autowired
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    //注入明细Mapper
    @Autowired
    private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
    //库存Mapper
    @Autowired
    private ProductStockMapper productStockMapper;
    //销售账单
    @Autowired
    private SaleAccountMapper saleAccountMapper;




    @Override
    public void insertOrUpdate(StockOutcomeBill entity) {
        if (entity.getId() == null) {
            saveBill(entity);
        }else{
            updateBill(entity);
        }
    }

    //订单更新
    private void updateBill(StockOutcomeBill entity) {
        //根据单据的ID查询完整信息
        StockOutcomeBill old = (StockOutcomeBill) stockOutcomeBillMapper.selectByPrimaryKey(entity.getId());

        //检测当前订单的状态, 未审核才能更新
      if(StockOutcomeBill.NORMAL.equals(old.getStatus())){
          //设置明细数据
          //定义变量  来接收记录总数量和总价格
          BigDecimal totalNumber = BigDecimal.ZERO;
          BigDecimal totalAmount = BigDecimal.ZERO;

          //更新前删除当前订单中的所有明细
          stockOutcomeBillItemMapper.deleteItemByBillId(entity.getId());

          for (StockOutcomeBillItem item : entity.getItems()) {
              //计算明细中的小计
              BigDecimal salePrice = item.getSalePrice();
              BigDecimal number = item.getNumber();

              //相乘
              BigDecimal amount = salePrice.multiply(number).setScale(2, RoundingMode.HALF_UP);

              //设置小计  -- 明细
              item.setAmount(amount);

              //累加总数量 总记录数
              totalNumber =  totalNumber.add(number);
              totalAmount = totalAmount.add(amount);

              //设置ID
              item.setBillId(old.getId());
              //保存
              stockOutcomeBillItemMapper.insert(item);
          }

          //设置当前订单的总价 和 总数量--更新
          entity.setTotalNumber(totalNumber);
          entity.setTotalAmount(totalAmount);
          stockOutcomeBillMapper.updateByPrimaryKey(entity);
      }


    }
    //订单新增
    private void saveBill(StockOutcomeBill entity) {
        //1设置录入人
        entity.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        entity.setInputTime(new Date());
        //设置单据状态
        entity.setStatus(StockOutcomeBill.NORMAL);

        //设置明细数据
        //定义变量  来接收记录总数量和总价格
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockOutcomeBillItem item : entity.getItems()) {
            //计算明细中的小计
            BigDecimal salePrice = item.getSalePrice();
            BigDecimal number = item.getNumber();

            //相乘
            BigDecimal amount = salePrice.multiply(number).setScale(2, RoundingMode.HALF_UP);
            item.setAmount(amount);

            //累加总数量 总记录数
            totalNumber =  totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        //先保存订单 因为保存明细需要订单的ID
        //设置当前订单的总价 和 总数量
        entity.setTotalNumber(totalNumber);
        entity.setTotalAmount(totalAmount);
        stockOutcomeBillMapper.insert(entity);

        //保存明细
        for (StockOutcomeBillItem item : entity.getItems()) {
            item.setBillId(entity.getId());

            //保存
            stockOutcomeBillItemMapper.insert(item);

        }

    }


    @Override
    public void deleteById(Long id) {

        //根据单据的ID查询完整信息
        StockOutcomeBill old = stockOutcomeBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能删除
        if(StockOutcomeBill.NORMAL.equals(old.getStatus())){
            //删除当前订单的所有明细 在删除订单
            stockOutcomeBillItemMapper.deleteItemByBillId(id);

            stockOutcomeBillMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public StockOutcomeBill getById(Long id) {
        return stockOutcomeBillMapper.selectByPrimaryKey(id);
    }



    @Override
    public PageResult queryAll(StockOutcomeBillQueryObject qo) {
        int count = stockOutcomeBillMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutcomeBill> data = stockOutcomeBillMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    //审核
    @Override
    public void updataAuditorById(Long id) {
        //根据单据的ID查询完整信息
        StockOutcomeBill bill = stockOutcomeBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能审核
        if(StockOutcomeBill.NORMAL.equals(bill.getStatus())){


            //获取当前单据的货物仓库对象
            Depot depot = bill.getDepot();

            //迭代当前单据中所有的明细 依次出仓库
            for (StockOutcomeBillItem item : bill.getItems()) {
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
            bill.setStatus(StockOutcomeBill.AUDIT);
            stockOutcomeBillMapper.updataAuditorById(bill);
        }
    }


