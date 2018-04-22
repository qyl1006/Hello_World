package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.query.ProductQueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    //分页/高级查询
    int queryCount(ProductQueryObject qo);
    List<Product> queryAll(ProductQueryObject qo);

}