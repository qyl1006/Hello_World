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
    private ApplicationContext ctx;



    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteById(id);
    }

    //分页----PageResult
    @Override
    public PageResult queryAll(QueryObject qo) {

        Integer count = permissionMapper.queryCount(qo);
        if (count == 0){
            return PageResult.EMPTY_PAGE;
        }

        List<?> data = permissionMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(),count,data);
    }

    //加载权限的业务方法
    @Override
    public void reload() {
        //查询系统中已经拥有的权限表达式
        List<String> exps = permissionMapper.selectAllExpressions();
        //从容器中获取所有的控制器
        Collection<Object> ctrls = ctx.getBeansWithAnnotation(Controller.class).values();
        //迭代每一个控制器的每一个方法
        for (Object ctrl : ctrls) {
            Method[] ms = ctrl.getClass().getDeclaredMethods();
            for (Method m : ms) {
                String exp = PermissionUtil.builExpression(m);
                //判断方法是否有权限注解
                RequiredPermission anno = m.getAnnotation(RequiredPermission.class);
                //有贴权限注解,并且该权限在数据库不存在
                if(anno != null && !exps.contains(exp)){
                    //生产uige权限对象
                    Permission p = new Permission();
                    //设置权限名称
                    p.setName(anno.value());
                    //设置权限表达式
                    p.setExpression(exp);
                    //保存到数据库
                    permissionMapper.insert(p);
                }
            }
        }
    }

    //   11
    @Override
    public List<Permission> listAll() {
        return permissionMapper.listAll();
    }

}
