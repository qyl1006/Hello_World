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


    @Override
    public void insertOrUpdate(ProductStock entity) {
        if (entity.getId() == null) {
            productStockMapper.insert(entity);
        }else{
            productStockMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        productStockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductStock getById(Long id) {
        return productStockMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(ProductStockQueryObject qo) {
        int count = productStockMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> data = productStockMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
    
}
