package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IRoleService {
    void insertOrUpdate(Role entity, Long[] ids, Long[] menuIds);

    void deleteById(Long id);

    Role getById(Long id);

    List<Role> listAll();

    //分页
    PageResult queryAll(QueryObject qo);

}
