package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface IRoleService {
    void insertOrUpdate(Role entity, Long[] permissionIds);
    void deleteById(Long id);
    Role getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Role> listAll();
}
