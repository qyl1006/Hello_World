package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    //分页
    int queryCount(QueryObject qo);
    List<Brand> queryAll(QueryObject qo);

//    /查所有品牌给商品表用
    List<Brand> listAll();
}