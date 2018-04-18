package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock record);

    ProductStock selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductStock record);


    //分页
    int queryCount(ProductStockQueryObject qo);
    List<ProductStock> queryAll(ProductStockQueryObject qo);

    //根据仓库ID 和商品ID查
    ProductStock selectByProductAndDepot(@Param("productId") Long productId,
                                         @Param("depotId") Long depotId);
}