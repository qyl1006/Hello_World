package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.domain.Systemmenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemmenuService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemmenuService systemmenuService;

    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = roleService.queryAll(qo);
        model.addAttribute("result", result);

        return "role/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Role entity = roleService.getById(id);
            model.addAttribute("entity",entity);
        }

        //权限
        List<Permission> permissions = permissionService.listAll();
        model.addAttribute("permissions", permissions);

        //菜单
        List<Systemmenu> menus = systemmenuService.listAll();
        model.addAttribute("menus", menus);
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Role entity, Long[] ids, Long[] menuIds){
        roleService.insertOrUpdate(entity, ids, menuIds);

        return "redirect:/role/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            roleService.deleteById(id);
        }

        return new JSONResult();
    }

}
