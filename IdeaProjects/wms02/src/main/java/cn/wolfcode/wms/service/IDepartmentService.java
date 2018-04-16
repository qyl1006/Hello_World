package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void insertOrUpdate(Department entity);

    void deleteById(Long id);

    Department getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Department> listAll();
}
