package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.mapper.OrderBillItemMapper;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;
    //注入明细Mapper
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;


    @Override
    public void insertOrUpdate(OrderBill entity) {
        if (entity.getId() == null) {
            saveBill(entity);
        }else{
            updateBill(entity);
        }
    }

    //订单更新
    private void updateBill(OrderBill entity) {
        //根据单据的ID查询完整信息
        OrderBill old = orderBillMapper.selectByPrimaryKey(entity.getId());

        //检测当前订单的状态, 未审核才能更新
      if(OrderBill.NORMAL.equals(old.getStatus())){
          //设置明细数据
          //定义变量  来接收记录总数量和总价格
          BigDecimal totalNumber = BigDecimal.ZERO;
          BigDecimal totalAmount = BigDecimal.ZERO;

          //更新前删除当前订单中的所有明细
          orderBillItemMapper.deleteItemByBillId(entity.getId());

          for (OrderBillItem item : entity.getItems()) {
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
          old.setSupplier(entity.getSupplier());
          old.setVdate(entity.getVdate());

          old.setTotalNumber(totalNumber);
          old.setTotalAmount(totalAmount);
          orderBillMapper.updateByPrimaryKey(old);
      }


    }
    //订单新增
    private void saveBill(OrderBill entity) {
        //1设置录入人
        entity.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        entity.setInputTime(new Date());
        //设置单据状态
        entity.setStatus(OrderBill.NORMAL);

        //设置明细数据
        //定义变量  来接收记录总数量和总价格
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderBillItem item : entity.getItems()) {
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
        orderBillMapper.insert(entity);

        //保存明细
        for (OrderBillItem item : entity.getItems()) {
            item.setBillId(entity.getId());

            //保存
            orderBillItemMapper.insert(item);

        }

    }


    @Override
    public void deleteById(Long id) {

        //根据单据的ID查询完整信息
        OrderBill old = orderBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能删除
        if(OrderBill.NORMAL.equals(old.getStatus())){
            //删除当前订单的所有明细 在删除订单
            orderBillItemMapper.deleteItemByBillId(id);

            orderBillMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public OrderBill getById(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(OrderBillQueryObject qo) {
        int count = orderBillMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<OrderBill> data = orderBillMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    //审核
    @Override
    public void updataAuditorById(Long id) {
        //根据单据的ID查询完整信息
        OrderBill old = orderBillMapper.selectByPrimaryKey(id);

        //检测当前订单的状态, 未审核才能审核
        if(OrderBill.NORMAL.equals(old.getStatus())){
            old.setStatus(OrderBill.AUDIT);
            old.setAuditTime(new Date());
            old.setAuditor(UserContext.getCurrentUser());

            //保存数据库
            orderBillMapper.updataAuditorById(old);
        }
    }


}
