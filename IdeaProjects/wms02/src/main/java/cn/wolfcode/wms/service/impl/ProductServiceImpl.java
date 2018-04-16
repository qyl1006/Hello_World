package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public void insertOrUpdate(Product entity) {
        if (entity.getId() == null) {
            productMapper.insert(entity);
        }else{
            productMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Product getById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(ProductQueryObject qo) {
        int count = productMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Product> data = productMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

}
