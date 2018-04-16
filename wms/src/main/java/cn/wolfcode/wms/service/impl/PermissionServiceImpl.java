package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PageResult;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext ctx;

    public void reload() {
        //1:先查询出数据库所有的权限
        List<String> exps = permissionMapper.selectAllExpressions();
        //2:从容器中找到所有的控制器,迭代每一个方法
        Collection<Object> ctrls = ctx.getBeansWithAnnotation(Controller.class).values();
        for (Object ctrl : ctrls) {
            Method[] ms = ctrl.getClass().getDeclaredMethods();
            for (Method m : ms) { //判断每一个方法是否需要生成权限
                String exp = PermissionUtil.buildExpression(m);
                RequiredPermission anno = m.getAnnotation(RequiredPermission.class);
                if (anno != null && !exps.contains(exp)) {
                    //生成权限对象
                    Permission p = new Permission();
                    p.setName(anno.value());
                    p.setExpression(exp);
                    //保存到数据库
                    permissionMapper.insert(p);
                }
            }
        }
    }

    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    public List<Permission> list() {
        return permissionMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        //总记录数
        Integer rows = permissionMapper.queryForCount(qo);
        if (rows == 0) {
            return PageResult.EMPTY_PAGE;
        }
        //----------------------------
        List<?> data = permissionMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }
}
