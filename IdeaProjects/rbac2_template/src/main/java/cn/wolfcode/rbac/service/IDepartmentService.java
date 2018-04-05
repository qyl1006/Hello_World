package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;


/**
 * @author Qyuelin
 */
public interface IDepartmentService {
    /**
     * 根据传入对象是否有id来保存或者更新部门信息
     * @param entuty 一个部门对象
     */
    void insertOrUpdate(Department entuty);

    /**
     * 根据ID删除部门信息
     * @param id 部门ID
     */
    void deleteById(Long id);

    /**
     * 查询一个部门信息
     * @param id 部门ID
     * @return
     */
    Department getById(Long id);

    /**
     * 查询分页条件下的PageResult对象
     * @param qo 分页对象
     * @return 返回每一页的数据对象PageResult
     */
    PageResult queryAll(QueryObject qo);

    /**
     * 查询所有部门信息
     * @return 返回部门对象的list集合
     */
    List<Department> list();
}
