package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = departmentService.queryAll(qo);
        model.addAttribute("result", result);
        return "department/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Department entity = departmentService.getById(id);
            model.addAttribute("entity", entity);
        }
        return "department/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Department entity){
        departmentService.insertOrUpdate(entity);
        return "redirect:/department/list.do";
    }

    @RequestMapping("delete")
    public String delete(Long id){
        departmentService.deleteById(id);
        return "redirect:/department/list.do";
    }
}
