package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    void reload();

    void deleteById(Long id);

    //分页
    PageResult queryAll(QueryObject qo);

    List<Permission> listAll();

    List<String> selectExpressionByEmployeeId(Long id);
}
