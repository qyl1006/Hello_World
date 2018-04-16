package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Systemmenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ParentQuery;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ISystemmenuService {
    void insertOrUpdate(Systemmenu entity);

    void deleteById(Long id);

    Systemmenu getById(Long id);


    List<Systemmenu> listAll();

    List<Systemmenu> selectByParent(ParentQuery qo);

    Systemmenu selectParentByKey(Long parentId);

    List<Systemmenu> getParentMenus(Long parentId);

    List<Map<String,Object>> getMenuBySn(String menuSn);

    List<Map<String,Object>> getMenuBySnAndUser(String menuSn, Long id);
}
