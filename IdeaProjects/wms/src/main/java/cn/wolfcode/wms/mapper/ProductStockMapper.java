package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {

    int insert(ProductStock record);

    ProductStock selectByPrimaryKey(Long id);


    int updateByPrimaryKey(ProductStock record);

    //分页/高级查询
    int queryCount(ProductStockQueryObject qo);
    List<ProductStock> queryAll(ProductStockQueryObject qo);

    //根据仓库ID 和商品ID查
    ProductStock selectByProductAndDepot(@Param("productId") Long productId,
                                         @Param("depotId") Long depotId);
}