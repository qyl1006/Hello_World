package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.OrderBillQueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    //更新
    int updateByPrimaryKey(OrderBill record);

    //分页
    int queryCount(OrderBillQueryObject qo);
    List<OrderBill> queryAll(OrderBillQueryObject qo);

    //审核
    void updataAuditorById(OrderBill old);
}