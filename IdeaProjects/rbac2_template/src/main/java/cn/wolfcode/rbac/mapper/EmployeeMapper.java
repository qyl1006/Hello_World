package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    void insert(Employee entity);
    void updateByPrimaryKey(Employee entity);
    void deleteByPrimarId(Long id);

    Employee getByPrimarId(Long id);

    Integer queryCount(EmployeeQueryObject qo);
    List<?> query(EmployeeQueryObject qo);

    void deleteRelation(Long id);

    //保存角色和权限的关系 employee role关系中间表
    void insertRelation(@Param("employeeId") Long employeeId,
                        @Param("roleId") Long roleId);

    Employee selectEmployeeByInfo(@Param("username") String username,
                                  @Param("password") String password);
}
