package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    public void saveOrUpdate(Product entity) {
        if (entity.getId() == null) {
            productMapper.insert(entity);
        } else {
            productMapper.updateByPrimaryKey(entity);
        }
    }

    public void delete(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public Product get(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public List<Product> list() {
        return productMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        //总记录数
        Integer rows = productMapper.queryForCount(qo);
        if (rows == 0) {
            return PageResult.EMPTY_PAGE;
        }
        //----------------------------
        List<?> data = productMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }
}
