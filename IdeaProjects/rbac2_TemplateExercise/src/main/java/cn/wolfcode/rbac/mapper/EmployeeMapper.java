package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    void insert(Employee entity);
    void updateByKey(Employee entity);
    void deleteById(Long id);
    Employee getById(Long id);

    Integer queryCount(EmployeeQueryObject qo);
    List<Employee> queryAll(EmployeeQueryObject qo);

    //更新前, 删除中间表关系
    void deleteRelation(Long id);

//    最后发额外的sql语句  维护外键关系
    void insertRelation(@Param("employeeId") Long employeeId,
                        @Param("roleId") Long roleId);

    //查询数据库  验证登陆信息
    Employee selectEmployeeByInfo(@Param("username") String username,
                                  @Param("password") String password);

}
