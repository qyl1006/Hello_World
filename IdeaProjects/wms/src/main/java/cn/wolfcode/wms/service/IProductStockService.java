package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IProductStockService {

    //分页
    PageResult queryAll(ProductStockQueryObject qo);

}
