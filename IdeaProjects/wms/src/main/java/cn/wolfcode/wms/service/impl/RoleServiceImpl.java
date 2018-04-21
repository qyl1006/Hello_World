package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    //菜单
    @Autowired
    private SystemMenuMapper systemMenuMapper;


    @Override
    public void insertOrUpdate(Role entity, Long[] ids, Long[] menuIds ) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        }else{
            //先删除旧关系--权限
            roleMapper.deleteRelation(entity.getId());
            //先删除旧关系--菜单
            systemMenuMapper.deleteRelation(entity.getId());

            roleMapper.updateByPrimaryKey(entity);
        }

        //维护关系--权限
        if (ids != null) {
            for (Long permissionId : ids) {
                roleMapper.insertRelation(entity.getId(), permissionId);
            }
        }

        //维护关系--菜单
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                systemMenuMapper.insertRelation(entity.getId(), menuId);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        //先删除旧关系
        roleMapper.deleteRelation(id);
        //先删除旧关系--菜单
        systemMenuMapper.deleteRelation(id);

        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }


    //分页
    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = roleMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = roleMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
}
