package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockInComebillItem;

public interface StockInComebillItemMapper {
    //根据订单ID删除其拥有的明细
    int deleteByPrimaryKey(Long billId);

    int insert(StockInComebillItem record);
    
}