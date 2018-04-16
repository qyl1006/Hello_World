package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.PermissionUtil;
import cn.wolfcode.rbac.util.RequiredPermission;
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
    //用与获取交给spring管理的所有控制器
    private ApplicationContext ctx;

    //加载权限的具体实现
    @Override
    public void reload() {
        //查询系统中已经拥有的权限表达式
        List<String> exps = permissionMapper.getListAll();
        //从容器中获取所有的控制器
        Collection<Object> ctrls = ctx.getBeansWithAnnotation(Controller.class).values();
        //迭代每一个控制器的每一个方法
        for (Object ctrl : ctrls) {
            Method[] ms = ctrl.getClass().getDeclaredMethods();
            for (Method m : ms) {
                //获取方法上的权限全限定名(自定义的  util包中)
                String exp = PermissionUtil.builExpression(m);
                //判断方法是否有权限注解
                RequiredPermission anno = m.getAnnotation(RequiredPermission.class);
                //有贴权限注解, 并且该权限注解在数据库不存在
                if(anno != null && !exps.contains(exp)){
                    //new权限类对象
                    Permission p = new Permission();
                    //设置权限的名称
                    p.setName(anno.value());
                    //设置权限表达式
                    p.setExpression(exp);
                    //保存到数据库
                    permissionMapper.insert(p);

                }
            }
        }
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.listAll();
    }

    //根据员工ID查询其拥有的权限
    @Override
    public List<String> selectExpressionsByEmployeeId(Long id) {
        return permissionMapper.selectExpressionsByEmployeeId(id);
    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteById(id);
    }


    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = permissionMapper.queryCount(qo);
        if (count == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = permissionMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), count, data);
    }




}
