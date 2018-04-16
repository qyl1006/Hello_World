package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Systemmenu;
import cn.wolfcode.wms.query.ParentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemmenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemmenu record);

    Systemmenu selectByPrimaryKey(Long id);

    List<Systemmenu> selectAll();

    int updateByPrimaryKey(Systemmenu record);

    List<Systemmenu> selectByParent(ParentQuery qo);

    //根据ID查询父菜单
    Systemmenu selectParentByKey(Long parentId);

    //删除旧子菜单
    void deleteSubmenu(Long id);

    //查询所有的完整父菜单对象
    Systemmenu selectParents(Long parentId);

    //所有菜单
    List<Map<String,Object>> getMenuBySn(String menuSn);

    //当前登陆拥有的菜单
    List<Map<String,Object>> getMenuBySnAndUser(@Param("menuSn") String menuSn,
                                                @Param("employeeId") Long employeeId);
}