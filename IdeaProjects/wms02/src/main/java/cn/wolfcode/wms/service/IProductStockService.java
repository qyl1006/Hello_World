package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IProductStockService {
    void insertOrUpdate(ProductStock entity);

    void deleteById(Long id);

    ProductStock getById(Long id);

    PageResult queryAll(ProductStockQueryObject qo);

}
