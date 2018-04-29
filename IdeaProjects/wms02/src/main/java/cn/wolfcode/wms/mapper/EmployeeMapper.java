package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    //分页
    int queryCount(EmployeeQueryObject qo);
    List<Employee> queryAll(EmployeeQueryObject qo);

    void deleteRelation(Long id);

    void insertRelation(@Param("employeeId") Long employeeId,
                        @Param("roleId") Long roleId);

    Employee selectByInfo(@Param("username") String username,
                          @Param("password") String password);

    //批量删除
    void batchDelete(Long[] ids);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Employee selectByUsername(String username);
}