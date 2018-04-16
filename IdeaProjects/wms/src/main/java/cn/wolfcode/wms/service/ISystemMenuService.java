package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ParentQueryObject;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

public interface ISystemMenuService {
    void insertOrUpdate(SystemMenu entity);

    void deleteById(Long id);

    SystemMenu getById(Long id);

    List<SystemMenu> listAll(ParentQueryObject qo);


    SystemMenu getParentById(Long parentId);

    List<SystemMenu> getParentMenus(Long parentId);
}
