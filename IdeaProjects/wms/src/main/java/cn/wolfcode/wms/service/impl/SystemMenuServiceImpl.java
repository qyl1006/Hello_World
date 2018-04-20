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
        //找出所有子菜单的ID
        List<Long> ids = new ArrayList();
        //根据父菜单ID查询出直接子菜单
        List<SystemMenu> childs = systemMenuMapper.selectChildByparentId(id);

        ids = testDelete(ids, childs);


        //删除所有子菜单
       systemMenuMapper.deleteChildByChildtId(ids);
        systemMenuMapper.deleteByPrimaryKey(id);
    }

    private List<Long> testDelete(List<Long> ids, List<SystemMenu> childs) {
        for (SystemMenu child : childs) {
             if (child != null) {
                ids.add(child.getId());
                //继续查下一个子菜单
                childs = systemMenuMapper.selectChildByparentId(child.getId());
                testDelete(ids, childs);
            }
        }
        return ids;
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

