package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.query.StockOutComeBillQueryObject;

import java.util.List;

public interface StockOutComeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutComeBill record);

    StockOutComeBill selectByPrimaryKey(Long id);

    int updateByPrimaryKey(StockOutComeBill record);

    //分页/高级查询
    int queryCount(StockOutComeBillQueryObject qo);
    List<StockOutComeBill> selectAll(StockOutComeBillQueryObject qo);


    //主要用于操作业务层检查订单是否审核状态的--更新订单用
    StockOutComeBill selectCheckStatusById(Long id);

    //审核
    void updateAuditorById(StockOutComeBill old);
}