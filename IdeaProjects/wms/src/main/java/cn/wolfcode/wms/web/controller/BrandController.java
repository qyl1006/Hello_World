package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;
    
    
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = brandService.queryAll(qo);
        model.addAttribute("result", result);

        return "brand/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Brand entity = brandService.getById(id);
            model.addAttribute("entity", entity);
        }

        return "brand/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Brand entity){
        brandService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            brandService.deleteById(id);
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
