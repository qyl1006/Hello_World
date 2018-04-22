package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;
import cn.wolfcode.wms.query.PageResult;

public interface lStockInComeBillService {
    void insertOrUpdate(StockInComeBill entity);

    void deleteById(Long id);

    StockInComeBill getById(Long id);

    //分页
    PageResult queryAll(StockInComeBillQueryObject qo);

    void updataAuditorById(Long id);
}
