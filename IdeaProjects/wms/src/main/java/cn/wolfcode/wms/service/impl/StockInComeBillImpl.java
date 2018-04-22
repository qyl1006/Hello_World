package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.domain.StockInComebillItem;
import cn.wolfcode.wms.mapper.StockInComeBillMapper;
import cn.wolfcode.wms.mapper.StockInComebillItemMapper;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.lStockInComeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class StockInComeBillImpl implements lStockInComeBillService {
    @Autowired
    private StockInComeBillMapper stockInComeBill;
    //明细
    @Autowired
    private StockInComebillItemMapper stockInComebillItemMapper;

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
        //根据订单的ID查询完整的信息
        StockInComeBill old = stockInComeBill.selectCheckStatusById(entity.getId());

        //检查订单状态 未审核才能更新
        if (StockInComeBill.NORMAL.equals(old.getStatus())) {
            //设置明细数据
            //定义变量 记录订单的总数量 总金额
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            //更新前删除当前订单中所有的明细
            stockInComebillItemMapper.deleteByPrimaryKey(entity.getId());

            for (StockInComebillItem item : entity.getItems()) {
                //计算明细中的小计
                BigDecimal costPrice = item.getCostPrice();
                BigDecimal number = item.getNumber();

                BigDecimal amount = costPrice.multiply(number).setScale(2,RoundingMode.HALF_UP);
                item.setAmount(amount);

                //累加订单中的总数量 总金额
                totalAmount = totalAmount.add(amount);
                totalNumber = totalNumber.add(number);

                //保存明细 有订单ID
                item.setBillId(old.getId());
                stockInComebillItemMapper.insert(item);

            }
            //设置订单
            entity.setTotalNumber(totalNumber);
            entity.setTotalAmount(totalAmount);

            stockInComeBill.updateByPrimaryKey(entity);
        }
    }


    //订单新增
    private void saveBill(StockInComeBill entity) {
        //设置录入人
        entity.setInputUser(UserContext.getCurrentUser());
        //设置录入时间
        entity.setInputTime(new Date());
        //设置单据状态
        entity.setStatus(StockInComeBill.NORMAL);

        //设置计算明细数据
        //定义变量 来接收当前订单的商品总数量和总价格
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockInComebillItem item : entity.getItems()) {
            //计算明细中的小计
            BigDecimal costPrice = item.getCostPrice();
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
        stockInComeBill.insert(entity);

        //保存明细
        for (StockInComebillItem item : entity.getItems()) {
            item.setBillId(entity.getId());

            stockInComebillItemMapper.insert(item);
        }
    }

    @Override
    public void deleteById(Long id) {
        //先删除当前订单下的所有明细
        stockInComebillItemMapper.deleteByPrimaryKey(id);

        stockInComeBill.deleteByPrimaryKey(id);
    }

    @Override
    public StockInComeBill getById(Long id) {
        return stockInComeBill.selectByPrimaryKey(id);
    }



    //分页
    @Override
    public PageResult queryAll(StockInComeBillQueryObject qo) {
        Integer count = stockInComeBill.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<StockInComeBill> data = stockInComeBill.selectAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    //订单审核
    @Override
    public void updataAuditorById(Long id) {
        StockInComeBill old = stockInComeBill.selectCheckStatusById(id);

        //检查当前订单的状态 未审核才能审核
        if (StockInComeBill.NORMAL.equals(old.getStatus())) {
            old.setStatus(StockInComeBill.AUDIT);
            old.setAuditTime(new Date());
            old.setAuditor(UserContext.getCurrentUser());

            //保存到数据库
            stockInComeBill.updateAuditorById(old);
        }
    }
}
