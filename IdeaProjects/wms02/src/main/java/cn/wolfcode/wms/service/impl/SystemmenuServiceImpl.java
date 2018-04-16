package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Systemmenu;
import cn.wolfcode.wms.mapper.SystemmenuMapper;
import cn.wolfcode.wms.query.ParentQuery;
import cn.wolfcode.wms.service.ISystemmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SystemmenuServiceImpl implements ISystemmenuService {
    @Autowired
    private SystemmenuMapper systemmenuMapper;


    @Override
    public void insertOrUpdate(Systemmenu entity) {
        if (entity.getId() == null) {
            systemmenuMapper.insert(entity);
        }else{
            systemmenuMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        //删除旧子菜单
        systemmenuMapper.deleteSubmenu(id);

        systemmenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Systemmenu getById(Long id) {
        return systemmenuMapper.selectByPrimaryKey(id);
    }



    @Override
    public List<Systemmenu> listAll() {
        return systemmenuMapper.selectAll();
    }

    @Override
    public List<Systemmenu> selectByParent(ParentQuery qo) {
        return systemmenuMapper.selectByParent(qo);
    }


    //根据ID查询父菜单 简单的
    @Override
    public Systemmenu selectParentByKey(Long parentId) {
        return systemmenuMapper.selectParentByKey(parentId);
    }

    //菜单层级
    @Override
    public List<Systemmenu> getParentMenus(Long parentId) {
        List<Systemmenu> menus = new ArrayList<>();
        Systemmenu menu = systemmenuMapper.selectParents(parentId);
        while(menu != null){
            menus.add(menu);

            menu = menu.getParent();
        }

        //反转集合
        Collections.reverse(menus);
        return menus;
    }


    //查所有菜单
    @Override
    public List<Map<String, Object>> getMenuBySn(String menuSn) {
        return systemmenuMapper.getMenuBySn(menuSn);
    }

    //查当前登陆用户拥有的菜单
    @Override
    public List<Map<String, Object>> getMenuBySnAndUser(String menuSn, Long id) {
        return systemmenuMapper.getMenuBySnAndUser(menuSn, id);
    }
}
