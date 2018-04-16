package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

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
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model) throws Exception {
        model.addAttribute("result", employeeService.query(qo));
        model.addAttribute("depts",departmentService.list());
        return "employee/list";
    }

    @RequestMapping("input")
    @RequiredPermission("员工编辑")
    public String input(Long id, Model model) throws Exception {
        if (id != null) {
            model.addAttribute("entity", employeeService.get(id));
        }
        model.addAttribute("depts",departmentService.list());
        model.addAttribute("roles",roleService.list());
        return "employee/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Employee entity, Long[] ids) throws Exception {
        employeeService.saveOrUpdate(entity, ids);
        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) throws Exception {
        if (id != null) {
            employeeService.delete(id);
        }
        return new JSONResult();
    }

    /**
     * 批量删除
     * @param ids ids[]: 2 ids[]: 3
       $.ajaxSettings.traditional = true;
     *            禁用jQuery数组参数加[]的功能
     * @return
     * @throws Exception
     */
    @RequestMapping("batchDelete")
    @ResponseBody
    public Object batchDelete(Long[] ids) throws Exception {
        if (ids != null) {
            employeeService.batchDelete(Arrays.asList(ids));
        }
        return new JSONResult();
    }
}
