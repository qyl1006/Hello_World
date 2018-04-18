package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;

import java.util.List;

public interface StockInComeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockInComeBill record);

    StockInComeBill selectByPrimaryKey(Long id);

    //更新
    int updateByPrimaryKey(StockInComeBill record);

    //分页
    int queryCount(StockInComeBillQueryObject qo);
    List<StockInComeBill> queryAll(StockInComeBillQueryObject qo);

    //审核
    void updataAuditorById(StockInComeBill old);
}