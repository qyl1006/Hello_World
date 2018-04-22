package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;

import java.util.List;

public interface StockInComeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockInComeBill record);

    StockInComeBill selectByPrimaryKey(Long id);

    int updateByPrimaryKey(StockInComeBill record);

    //分页/高级查询
    int queryCount(StockInComeBillQueryObject qo);
    List<StockInComeBill> selectAll(StockInComeBillQueryObject qo);


    //主要用于操作业务层检查订单是否审核状态的--更新订单用
    StockInComeBill selectCheckStatusById(Long id);

    //审核
    void updateAuditorById(StockInComeBill old);
}