package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import java.util.List;

public interface OrderBillItemMapper {

    int insert(OrderBillItem record);


    void deleteItemByBillId(Long billId);

}