package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.OrderBillQueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderBill record);

    //分页/高级查询
    int queryCount(OrderBillQueryObject qo);
    List<OrderBill> selectAll(OrderBillQueryObject qo);


    //主要用于操作业务层检查订单是否审核状态的--更新订单用
    OrderBill selectCheckStatusById(Long id);

    //审核
    void updateAuditorById(OrderBill old);
}