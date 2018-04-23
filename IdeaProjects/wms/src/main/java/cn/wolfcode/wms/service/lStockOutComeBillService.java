package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutComeBillQueryObject;

public interface lStockOutComeBillService {
    void insertOrUpdate(StockOutComeBill entity);

    void deleteById(Long id);

    StockOutComeBill getById(Long id);

    //分页
    PageResult queryAll(StockOutComeBillQueryObject qo);

    void updataAuditorById(Long id);
}
