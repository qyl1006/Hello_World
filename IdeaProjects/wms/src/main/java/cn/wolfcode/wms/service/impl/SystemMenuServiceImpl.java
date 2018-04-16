package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ParentQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;


    @Override
    public void insertOrUpdate(SystemMenu entity) {
        if (entity.getId() == null) {
            systemMenuMapper.insert(entity);
        }else{
            systemMenuMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        //先删除所有子菜单
        systemMenuMapper.deleteChildByParentId(id);

        systemMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SystemMenu getById(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> listAll(ParentQueryObject qo) {
        return systemMenuMapper.selectAll(qo);
    }

//    查父菜单
    @Override
    public SystemMenu getParentById(Long parentId) {
        return systemMenuMapper.getParentById(parentId);
    }

    @Override
    public List<SystemMenu> getParentMenus(Long parentId) {
        List<SystemMenu> menuDirs = new ArrayList<>();
        SystemMenu menu = systemMenuMapper.getParentMenus(parentId);
        while (menu != null){
            menuDirs.add(menu);
            menu = menu.getParent();
        }

        //反转集合
        Collections.reverse(menuDirs);
        return menuDirs;
    }


}
