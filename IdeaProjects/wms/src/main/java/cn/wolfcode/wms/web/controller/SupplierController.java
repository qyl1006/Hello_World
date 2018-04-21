package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;
    
    
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = supplierService.queryAll(qo);
        model.addAttribute("result", result);

        return "supplier/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Supplier entity = supplierService.getById(id);
            model.addAttribute("entity", entity);
        }

        return "supplier/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Supplier entity){
        supplierService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            supplierService.deleteById(id);
        }

        return  new JSONResult();
    }


    @RequestMapping("test")
    @ResponseBody
    public Object test(String username){
        System.out.println(username);
        return new JSONResult();
    }


}
