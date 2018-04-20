package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;

import java.util.List;

public interface StockOutcomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    //更新
    int updateByPrimaryKey(StockOutcomeBill record);

    //分页
    int queryCount(StockOutcomeBillQueryObject qo);
    List<StockOutcomeBill> queryAll(StockOutcomeBillQueryObject qo);

    //审核
    void updataAuditorById(StockOutcomeBill old);
}