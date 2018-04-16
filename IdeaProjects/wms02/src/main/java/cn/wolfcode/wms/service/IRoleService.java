package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IRoleService {
    void insertOrUpdate(Role entity, Long[] menuIds, Long[] ids);

    void deleteById(Long id);

    Role getById(Long id);

    PageResult queryAll(QueryObject qo);

    List<Role> listAll();
}
