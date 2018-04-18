package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockInComeBillItem;

public interface StockInComeBillItemMapper {

    int insert(StockInComeBillItem record);


    void deleteItemByBillId(Long billId);

}