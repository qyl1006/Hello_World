package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IEmployeeService;
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
    private PermissionMapper permissionMapper;


    @Override
    public void insertOrUpdate(Employee entity, Long[] ids) {
        if (entity.getId() == null) {
            //MD5加密
            String pwdMD5 = MD5.encoder(entity.getPassword());
            entity.setPassword(pwdMD5);
            employeeMapper.insert(entity);
        }else{
            //先删除旧关系
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
        //先删除旧关系
        employeeMapper.deleteRelation(id);

        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }


    //高级查询/分页
    @Override
    public PageResult queryAll(EmployeeQueryObject qo) {
        int count = employeeMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Employee> data = employeeMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(),count,data);
    }

    @Override
    public void login(String username, String password) {
        //查数据库---密码加密后传入
        Employee emp = employeeMapper.selectByInfo(username, MD5.encoder(password));
        if(emp == null){
            throw new RuntimeException("账号密码错误--业务层提示");
        }

        //存入session
        UserContext.setCurrentUser(emp);

        if(emp.isAdmin()){
            return;
        }

        //查询当前用户拥有的权限
        List<String> exps = permissionMapper.selectByEmployeeId(emp.getId());
        UserContext.setExpression(exps);


    }

    //批量删除
    @Override
    public void batchDeleteByIds(Long[] ids) {
         employeeMapper.batchDeleteByIds(ids);
    }
}
