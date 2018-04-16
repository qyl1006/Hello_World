package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.Systemmenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ParentQuery;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISystemmenuService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("systemmenu")
public class SystemmenuController {
    @Autowired
    private ISystemmenuService systemmenuService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ParentQuery qo, Model model){
        //显示当前菜单层级
        if (qo.getParentId() != null) {
            List<Systemmenu> menus = systemmenuService.getParentMenus(qo.getParentId());
            model.addAttribute("menus", menus);
        }

        List<Systemmenu> systemmenus = systemmenuService.selectByParent(qo);
        model.addAttribute("systemmenus", systemmenus);

        return "systemmenu/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model, Long parentId){
        if (id != null) {
            Systemmenu entity = systemmenuService.getById(id);
            model.addAttribute("entity",entity);
        }

        if (parentId != null) {

            Systemmenu parent = systemmenuService.selectParentByKey(parentId);
            model.addAttribute("parent", parent);
        }

        return "systemmenu/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Systemmenu entity){
        systemmenuService.insertOrUpdate(entity);

        return "redirect:/systemmenu/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            systemmenuService.deleteById(id);
        }

        return new JSONResult();
    }


    //根据菜单的sn获取子菜单
    @RequestMapping("getmenusBySn")
    @ResponseBody
    public Object getMenusBySn(String menuSn){

        //取出当前登陆用户
        Employee emp = UserContext.getCurrentUser();

        if(emp.isAdmin()){
            //所有菜单
            return systemmenuService.getMenuBySn(menuSn);
        }
        return systemmenuService.getMenuBySnAndUser(menuSn,emp.getId());
    }

}
