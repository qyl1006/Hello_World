package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
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
        }else{
            departmentMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }


    //分页
    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = departmentMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Department> data = departmentMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }
}
