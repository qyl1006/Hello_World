package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public void insertOrUpdate(Department entity) {
        if (entity.getId() == null) {
            departmentMapper.insert(entity);
        }else {
            departmentMapper.updateByKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentMapper.getById(id);
    }

    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = departmentMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Department> data = departmentMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count,data);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.listAll();
    }
}
