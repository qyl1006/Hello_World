package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

/**操作角色的规范
 * @author Qyuelin
 */
public interface IRoleService {
    /**
     * 保存或更新角色信息
     * @param entuty 角色对象
     * @param permissionIds 权限ID的Long集合
     */
    void saveOrUpdate(Role entuty, Long[] permissionIds);

    /**
     * 删除角色
     * @param id ID
     */
    void deleteById(Long id);

    /**
     *查询一个角色
     * @param id ID
     * @return 角色对象
     */
    Role getByIId(Long id);

    /**
     * 分页
     * @param qo 分页大小
     * @return Pageresult对象
     */
    PageResult queryAll(QueryObject qo);

    /**
     * 查询所有角色信息
     * @return 返回所有角色list集合
     */
    List<Role> listAll();
}
