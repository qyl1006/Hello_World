package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;


    //保存/更新   角色 - 权限 多对多关系
    @Override
    public void insertOrUpdate(Role entity, Long[] permissionIds) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        }else {
            //先删除旧的关系--删除中间表的关系数据
            roleMapper.deleteRelation(entity.getId());
            roleMapper.updateByKey(entity);
        }
        //保存角色和权限的关系
        if (permissionIds != null) {
            for (Long pId : permissionIds) {
                roleMapper.insertRelation(entity.getId(), pId);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        //先删除旧的关系--删除中间表的关系数据
        roleMapper.deleteRelation(id);

        roleMapper.deleteById(id);
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.getById(id);
    }

    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = roleMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = roleMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count,data);
    }


    //查询所有角色 给员工input操作使用
    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

}
