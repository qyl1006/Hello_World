package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

/**
 * @author Qyuelin
 */
public interface DepartmentMapper {
    /**
     * 保存部门信息Mapper
     * @param entuty 一个部门对象
     */
    void insert(Department entuty);

    /**
     * 更新部门信息Mapper
     * @param entuty 部门对象
     */
    void updateByKey(Department entuty);

    /**
     * 删除部门信息Mapper
     * @param id 部门的ID
     */
    void deleteById(Long id);

    /**
     * 根据部门ID查询部门信息Mapper
     * @param id 部门ID
     * @return 部门对象
     */
    Department getById(Long id);

    /**
     * 分页使用,查询出总记录数Mapper
     * @param qo 分页对象
     * @return 总记录数 Integer
     */
    Integer queryCount(QueryObject qo);

    /**
     * 分页-查询所有部门信息Mapper
     * @param qo 分页对象
     * @return 返回所有部门信息的List集合
     */
    List<?>  queryAll(QueryObject qo);

    /**
     * 查询所有部门信息Mapper
     * @return 返回部门对象的List集合
     */
    List<Department> listAll();
}
