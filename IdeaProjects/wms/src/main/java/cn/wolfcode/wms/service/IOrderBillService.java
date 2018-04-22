package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IOrderBillService {
    void insertOrUpdate(OrderBill entity);

    void deleteById(Long id);

    OrderBill getById(Long id);

    //分页
    PageResult queryAll(OrderBillQueryObject qo);

}
