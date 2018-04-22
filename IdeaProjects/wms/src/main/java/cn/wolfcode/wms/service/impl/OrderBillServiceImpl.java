package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;


    @Override
    public void insertOrUpdate(OrderBill entity) {
        if (entity.getId() == null) {
            orderBillMapper.insert(entity);
        }else{
            orderBillMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        orderBillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderBill getById(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }



    //分页
    @Override
    public PageResult queryAll(OrderBillQueryObject qo) {
        Integer count = orderBillMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<OrderBill> data = orderBillMapper.selectAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
}
