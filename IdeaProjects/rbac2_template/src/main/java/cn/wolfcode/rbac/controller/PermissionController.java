package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    //加载权限
    @RequestMapping("reload")
    public ModelAndView reload(HttpServletResponse resp) throws IOException {
        permissionService.reload();
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().print("{\"success\":true}");
        return null;
    }


    //可分页的list
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = permissionService.queryAll(qo);
        model.addAttribute("result", result);
        return "permission/list";
    }


    //删除
    @RequestMapping("delete")
    public String delete(Long id){
        if (id != null){
            permissionService.deleteById(id);
        }
        return "redirect:/permission/list.do";
    }
}
