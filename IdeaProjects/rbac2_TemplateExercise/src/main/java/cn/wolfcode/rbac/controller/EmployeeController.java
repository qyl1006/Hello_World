package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
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

    //查询部门信息用
    @Autowired
    private IDepartmentService departmentService;

    //查询角色信息用
    @Autowired
    private IRoleService roleService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model){
        PageResult result = employeeService.queryAll(qo);
        model.addAttribute("result", result);
        model.addAttribute("depts", departmentService.listAll());
        return "employee/list";
    }

    @RequiredPermission("新增/编辑员工")
    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Employee entity = employeeService.getById(id);
            model.addAttribute("employee", entity);
        }
        model.addAttribute("depts", departmentService.listAll());

        model.addAttribute("roles", roleService.listAll());
        return "employee/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee entity, Long[] roleIds){
        employeeService.insertOrUpdate(entity, roleIds);
        return "redirect:/employee/list.do";
    }

    @RequiredPermission("删除员工")
    @RequestMapping("delete")
    public String delete(Long id){
        employeeService.deleteById(id);
        return "redirect:/employee/list.do";
    }
}
