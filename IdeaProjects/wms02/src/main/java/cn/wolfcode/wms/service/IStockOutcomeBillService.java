package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;

public interface IStockOutcomeBillService {
    void insertOrUpdate(StockOutcomeBill entity);

    void deleteById(Long id);

    StockOutcomeBill getById(Long id);

    PageResult queryAll(StockOutcomeBillQueryObject qo);

    void updataAuditorById(Long id);
}
