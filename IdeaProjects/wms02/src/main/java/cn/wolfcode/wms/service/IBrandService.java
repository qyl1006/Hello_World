package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IBrandService {
    void insertOrUpdate(Brand entity);

    void deleteById(Long id);

    Brand getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Brand> listAll();
}
