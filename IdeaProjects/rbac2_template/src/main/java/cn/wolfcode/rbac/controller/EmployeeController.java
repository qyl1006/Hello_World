package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model){
        PageResult results = employeeService.queryAll(qo);
        model.addAttribute("result", results);
        model.addAttribute("depts",departmentService.list());

        return "employee/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null){
            Employee employee = employeeService.get(id);
            model.addAttribute("employee", employee);
        }
        model.addAttribute("depts",departmentService.list());
        model.addAttribute("roles", roleService.listAll());
        return "employee/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee entity, Long[] roleIds){
//        employeeService.saveOrUpdate(entity, roleIds);
        return "redirect:/employee/list.do";
    }

    @RequestMapping("delete")
    public String delete(Long id){
        if (id != null){
            employeeService.delete(id);
        }
        return "redirect:/employee/list.do";
    }
}
