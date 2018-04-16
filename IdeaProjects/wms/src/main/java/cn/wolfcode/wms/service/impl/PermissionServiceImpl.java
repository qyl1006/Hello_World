package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequirePermission;
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

    //加载权限
    @Override
    public void reload() {
        //查询数据库中所有的权限
        List<String> exps = permissionMapper.getListAll();
        //从容器中获取所有的控制器
        Collection<Object> ctrs = ctx.getBeansWithAnnotation(Controller.class).values();
        //迭代每一个控制器的每一个方法
        for (Object ctr1 : ctrs){
            Method[] ms = ctr1.getClass().getDeclaredMethods();
            for (Method m : ms) {
                //获取方法上的权限表达式
                String exp = PermissionUtil.builExpression(m);

                //判断方法是否有权限
                RequirePermission anno = m.getAnnotation(RequirePermission.class);

                if(anno != null && !exps.contains(exp)){
                    Permission p = new Permission();
                    p.setExpression(exp);
                    p.setName(anno.value());

                    //保存
                    permissionMapper.insert(p);
                }
            }
        }
    }



    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }


    //分页
    @Override
    public PageResult queryAll(QueryObject qo) {
        Integer count = permissionMapper.queryCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = permissionMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }


}
