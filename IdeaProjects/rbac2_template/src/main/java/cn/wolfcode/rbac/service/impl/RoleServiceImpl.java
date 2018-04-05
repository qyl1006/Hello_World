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


    //  保存或更新  ---角色-权限多对多关系
    @Override
    public void saveOrUpdate(Role entity, Long[] permissionIds) {
        if (entity.getId() == null){
            roleMapper.insert(entity);
        }else{
            //删除旧的关系
            roleMapper.deleteRelation(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        }
        //保存角色和权限的关系
        if (permissionIds != null){
            for (Long pId : permissionIds) {
                roleMapper.insertRelation(entity.getId(), pId);
            }
        }
    }

    //  11
    @Override
    public void deleteById(Long id) {
        //删除旧的关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteById(id);
    }

    //   111
    @Override
    public Role getByIId(Long id) {
        return roleMapper.getByIId(id);
    }

    //分页----PageResult  11
    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = roleMapper.queryCount(qo);
        if (count == 0){
            return PageResult.EMPTY_PAGE;
        }

        List<?> data = roleMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(),count,data);
    }

    //查询所有角色---给员工类使用--新增编辑功能显示选择
    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

}
