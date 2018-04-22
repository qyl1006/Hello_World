package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface OrderBillItemMapper {
    //根据订单ID删除其拥有的明细
    int deleteByPrimaryKey(Long billId);

    int insert(OrderBillItem record);
    
}