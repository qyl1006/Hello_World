package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    List<SystemMenu> queryForMenu(SystemMenuQueryObject qo);

    /**
     * 根据父菜单删除子菜单
     * @param parentId
     */
    void deleteChildMenu(Long parentId);

    /**
     * 根据父菜单的SN查询子菜单
     * @param menuSn
     * @return
     */
    List<Map<String, Object>> selectMenuBySn(String menuSn);
    /**
     * 根据父菜单的SN和用户id查询子菜单
     * @param menuSn
     * @return
     */
    List<Map<String,Object>> selectMenuBySnAndUserId(@Param("menuSn") String menuSn,
                                                     @Param("empId") Long empId);
}