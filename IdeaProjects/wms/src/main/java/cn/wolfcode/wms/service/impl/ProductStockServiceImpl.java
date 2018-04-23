package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;

    //分页
    @Override
    public PageResult queryAll(ProductStockQueryObject qo) {
        Integer count = productStockMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> data = productStockMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
}
