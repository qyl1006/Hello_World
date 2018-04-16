package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            model.addAttribute("entity",entity);
        }

        return "department/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Department entity){
        departmentService.insertOrUpdate(entity);

        return "redirect:/department/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            departmentService.deleteById(id);
        }

        return new JSONResult();
    }

}
