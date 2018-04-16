package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    public void saveOrUpdate(Role entity, Long[] ids, Long[] menuIds) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        } else {
            roleMapper.deleteRelation(entity.getId());
            roleMapper.deleteMenuRelation(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        }
        //处理关联关系
        if (ids != null) {
            for (Long permissionId : ids) {
                roleMapper.insertRelation(entity.getId(), permissionId);
            }
        }
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                roleMapper.insertMenuRelation(entity.getId(), menuId);
            }
        }
    }

    public void delete(Long id) {
        //先删除关联关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteMenuRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> list() {
        return roleMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        //总记录数
        Integer rows = roleMapper.queryForCount(qo);
        if (rows == 0) {
            return PageResult.EMPTY_PAGE;
        }
        //----------------------------
        List<?> data = roleMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }
}
