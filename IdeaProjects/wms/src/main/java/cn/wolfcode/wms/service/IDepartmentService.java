package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void insertOrUpdate(Department entity);

    void deleteById(Long id);

    Department getById(Long id);

    List<Department> listAll();

    //分页
    PageResult queryAll(QueryObject qo);

}
