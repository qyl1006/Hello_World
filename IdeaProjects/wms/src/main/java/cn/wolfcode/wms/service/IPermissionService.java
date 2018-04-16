package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    void deleteById(Long id);


    List<Permission> listAll();

    //分页
    PageResult queryAll(QueryObject qo);

    //加载权限
    void reload();

}
