package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockInComeBillItemMapper;
import cn.wolfcode.wms.mapper.StockInComeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockInComeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockInComeBillService implements IStockInComeBillService {
    @Autowired
    private StockInComeBillMapper stockInComeBillMapper;
    //注入明细Mapper
    @Autowired
    private StockInComeBillItemMapper orderBillItemMapper;
    //库存Mapper
    @Autowired
    private ProductStockMapper productStockMapper;



    @Override
    public void insertOrUpdate(StockInComeBill entity) {
        if (entity.getId() == null) {
            saveBill(entity);
        }else{
            updateBill(entity);
        }
    }

    //订单更新
    private void updateBill(StockInComeBill entity) {
        //根据单据的ID查询完整信息
        StockInComeBill old = (StockInComeBill) stockInComeBillMapper.selectByPrimaryKey(entity.getId());

        //检测当前订单的状态, 未审核才能更新
      if(StockInComeBill.NORMAL.equals(old.getStatus())){
          //设置明细数据
          //定义变量  来接收记录总数量和总价格
          BigDecimal totalNumber = BigDecimal.ZERO;
          BigDecimal totalAmount = BigDecimal.ZERO;

          //更新前删除当前订单中的所有明细
          orderBillItemMapper.deleteItemByBillId(entity.getId());

          for (StockInComeBillItem item : entity.getItems()) {
              //计算明细中的小计
              BigDecimal costPrice = item.getCostPrice();
              BigDecimal number = item.getNumber();

              //相乘
              BigDecimal amount = costPrice.multiply(number).setScale(2, RoundingMode.HALF_UP);

              //设置小计  -- 明细
              item.setAmount(amount);

              //累加总数量 总记录数
              totalNumber =  totalNumber.add(number);
              totalAmount = totalAmount.add(amount);

              //设置ID
              item.setBillId(old.getId());
              //保存
              orderBillItemMapper.insert(item);
          }

          //设置当前订单的总价 和 总数量--更新
          old.setSn(entity.getSn());
          old.setDepot(entity.getDepot());
          old.setVdate(entity.getVdate());

          old.setTotalNumber(totalNumber);
          old.setTotalAmount(totalAmount);
          stockInComeBillMapper.updateByPrimaryKey(old);
      }


    }
    //订单新增
    private void saveBill(StockInComeBill entity) {
        //1设置录入人
        entity.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        entity.setInputTime(new Date());
        //设置单据状态
        entity.setStatus(StockInComeBill.NORMAL);

        //设置明细数据
        //定义变量  来接收记录总数量和总价格
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockInComeBillItem item : entity.getItems()) {
            //计算明细中的小计
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();

            //相乘
            BigDecimal amount = costPrice.multiply(number).setScale(2, RoundingMode.HALF_UP);
            item.setAmount(amount);

            //累加总数量 总记录数
            totalNumber =  totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        //先保存订单 因为保存明细需要订单的ID
        //设置当前订单的总价 和 总数量
        entity.setTotalNumber(totalNumber);
        entity.setTotalAmount(totalAmount);
        stockInComeBillMapper.insert(entity);

        //保存明细
        for (StockInComeBillItem item : entity.getItems()) {
            item.setBillId(entity.getId());

            //保存
            orderBillItemMapper.insert(item);

        }

    }


    @Override
    public void deleteById(Long id) {

        //根据单据的ID查询完整信息
        StockInComeBill old = stockInComeBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能删除
        if(StockInComeBill.NORMAL.equals(old.getStatus())){
            //删除当前订单的所有明细 在删除订单
            orderBillItemMapper.deleteItemByBillId(id);

            stockInComeBillMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public StockInComeBill getById(Long id) {
        return stockInComeBillMapper.selectByPrimaryKey(id);
    }



    @Override
    public PageResult queryAll(StockInComeBillQueryObject qo) {
        int count = stockInComeBillMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<StockInComeBill> data = stockInComeBillMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    //审核
    @Override
    public void updataAuditorById(Long id) {
        //根据单据的ID查询完整信息
        StockInComeBill bill = stockInComeBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能审核
        if(StockInComeBill.NORMAL.equals(bill.getStatus())){
            //获取当前单据的货物仓库对象
            Depot depot = bill.getDepot();

            //迭代当前单据中所有的明细 依次入仓库
            for (StockInComeBillItem item : bill.getItems()) {
                //获取单据货品对象
                Product p = item.getProduct();

                //查询当前商品所入库的仓库中是否拥有该商品的库存
                ProductStock ps = productStockMapper.selectByProductAndDepot(p.getId(), depot.getId());
                if (ps != null) {
                    //使用移动加权平均算法  重新计算库存商品价格 数量
                    BigDecimal amount = item.getAmount().add(ps.getAmount());
                    BigDecimal number = item.getNumber().add(ps.getStoreNumber());

                    //计算
                    BigDecimal price = amount.divide(number, 2, RoundingMode.HALF_UP);

                    //更新库存信息
                    ps.setPrice(price);
                    ps.setStoreNumber(number);
                    ps.setAmount(amount);

                    productStockMapper.updateByPrimaryKey(ps);

                }else {
                    //创建一个库存对象,, 存入数据库
                    ps = new ProductStock();
                    ps.setPrice(item.getCostPrice());
                    ps.setAmount(item.getAmount());
                    ps.setStoreNumber(item.getNumber());
                    ps.setProduct(p);
                    ps.setDepot(depot);

                    productStockMapper.insert(ps);
                }
            }

            //设置单据的审核人 时间 状态
            bill.setAuditTime(new Date());
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setStatus(StockInComeBill.AUDIT);
            stockInComeBillMapper.updataAuditorById(bill);



        }
    }


}
