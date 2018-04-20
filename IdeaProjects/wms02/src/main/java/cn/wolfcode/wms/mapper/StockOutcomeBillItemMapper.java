package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {

    int insert(StockOutcomeBillItem record);


    void deleteItemByBillId(Long billId);

}