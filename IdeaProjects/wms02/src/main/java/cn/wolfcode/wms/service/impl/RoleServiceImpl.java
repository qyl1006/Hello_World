package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
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


    @Override
    public void insertOrUpdate(Role entity, Long[] ids, Long[] menuIds) {
        if (entity.getId() == null) {
            roleMapper.insert(entity);
        }else{
            //删除旧关系
            roleMapper.deleteRelation(entity.getId());

            //删除旧关系
            roleMapper.deleteRelationByMenu(entity.getId());


            roleMapper.updateByPrimaryKey(entity);
        }
        //维护关系
        if (ids != null) {
            for (Long permissionId : ids) {
                roleMapper.insertRelation(entity.getId(), permissionId);
            }
        }

        //维护关系
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                roleMapper.insertRelationByMenu(entity.getId(), menuId);
            }
        }

    }

    @Override
    public void deleteById(Long id) {
        //删除旧关系
        roleMapper.deleteRelation(id);
        //删除旧关系
        roleMapper.deleteRelationByMenu(id);


        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(QueryObject qo) {
        int count = roleMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Role> data = roleMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    /**
     * 根据Employee的ID查询Role
     * @param EmployeeId
     * @return
     */
    @Override
    public List<String> selectSnByEmployeeId(Long EmployeeId) {
        return roleMapper.selectSnByEmployeeId(EmployeeId);
    }
}
