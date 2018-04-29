package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequirePermission;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
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
    public void reload() {
        //查数据库
        List<String> exps = permissionMapper.getListAll();
        //从容器中取 所有控制器
        Collection<Object> ctrs = ctx.getBeansWithAnnotation(Controller.class).values();
        //迭代 每一个控制器的每一个方法
        for (Object ctr : ctrs) {
            Method[] ms = ctr.getClass().getDeclaredMethods();
            for (Method m : ms) {
//                //生产 方法的权限表达式
//                String exp = PermissionUtil.builExpression(m);


                //判断方法是否有权限
                //anno取出来的为null 有问题需要解决
                RequiresPermissions anno = m.getAnnotation(RequiresPermissions.class);
                if (anno != null) {
                    String[] pemissionss = anno.value();
                    for (String pemission : pemissionss) {
                        if(!exps.contains(pemission)){
                            Permission p = new Permission();
                            p.setExpression(pemission);

                            System.out.println(pemission);
                            //保存
                            permissionMapper.insert(p);
                        }
                    }

                }


            }
        }

    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

   
    @Override
    public PageResult queryAll(QueryObject qo) {
        int count = permissionMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = permissionMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<String> selectExpressionByEmployeeId(Long id) {
        return permissionMapper.selectExpressionByEmployeeId(id);
    }
}
