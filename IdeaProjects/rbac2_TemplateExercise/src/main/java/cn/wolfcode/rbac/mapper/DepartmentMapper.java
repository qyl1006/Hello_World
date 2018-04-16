package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    void insert(Department entity);
    void updateByKey(Department entity);
    void deleteById(Long id);
    Department getById(Long id);

    Integer queryCount(QueryObject qo);
    List<Department> queryAll(QueryObject qo);

    List<Department> listAll();
}
