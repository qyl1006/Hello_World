package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    
    //加载权限
    @RequirePermission("加载权限")
    @RequestMapping("reload")
    @ResponseBody
    public Object reload(){
        permissionService.reload();


        return new JSONResult();
    }


    @RequirePermission("权限列表")
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = permissionService.queryAll(qo);
        model.addAttribute("result", result);

        return "permission/list";
    }

    
    @RequirePermission("删除权限")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            permissionService.deleteById(id);
        }

        return  new JSONResult();
    }
    

}
