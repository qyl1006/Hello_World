package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.MD5;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IPermissionService permissionService;




    @Override
    public void insertOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() == null) {
            //密码MD5加密
            String encoder = MD5.encoder(entity.getPassword());
            entity.setPassword(encoder);
            employeeMapper.insert(entity);
        }else{
            //删除旧关系
            employeeMapper.deleteRelation(entity.getId());

            employeeMapper.updateByPrimaryKey(entity);
        }
        //维护关系
        if (ids != null) {
            for (Long roleId : ids) {
                employeeMapper.insertRelation(entity.getId(), roleId);
            }
        }

    }

    @Override
    public void deleteById(Long id) {
        //删除旧关系
        employeeMapper.deleteRelation(id);

        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(EmployeeQueryObject qo) {
        int count = employeeMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = employeeMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    @Override
    public void login(String username, String password) {
        Employee emp = employeeMapper.selectByInfo(username, MD5.encoder(password));

        //存入session
        UserContext.setCurrentUser(emp);

        //判断
        if(emp == null){
            throw new RuntimeException("账号或密码错误");
        }

        //是否超管
        if(emp.isAdmin()){
            return;
        }

        //查当前登陆用户所有权限
        List<String> exps = permissionService.selectExpressionByEmployeeId(emp.getId());

        UserContext.setExpression(exps);

    }

    //批量删除
    @Override
    public void batchDelete(Long[] ids) {
        //删除旧关系
        for (Long id : ids) {

            employeeMapper.deleteRelation(id);
        }

        employeeMapper.batchDelete(ids);


    }
}
