package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    
    @RequirePermission("员工列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model){
        PageResult result = employeeService.queryAll(qo);
        model.addAttribute("result", result);

        //共享所有部门信息
        model.addAttribute("departments", departmentService.listAll());
        return "employee/list";
    }


    @RequirePermission("新增/编辑员工")
    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Employee entity = employeeService.getById(id);
            model.addAttribute("entity", entity);
        }

        //共享所有部门信息
        model.addAttribute("departments", departmentService.listAll());

        //角色
        model.addAttribute("roles", roleService.listAll());
        return "employee/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Employee entity, Long[] ids){
        employeeService.insertOrUpdate(entity, ids);

        return new JSONResult();
    }

    @RequirePermission("删除员工")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            employeeService.deleteById(id);
        }

        return new JSONResult();
    }

    
}
