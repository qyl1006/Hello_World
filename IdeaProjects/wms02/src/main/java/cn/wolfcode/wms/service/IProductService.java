package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IProductService {
    void insertOrUpdate(Product entity);

    void deleteById(Long id);

    Product getById(Long id);

    PageResult queryAll(ProductQueryObject qo);

}
