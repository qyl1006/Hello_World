package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void insertOrUpdate(Department entity);
    void deleteById(Long id);
    Department getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Department> listAll();
}
