package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("depot")
public class DepotController {
    @Autowired
    private IDepotService depotService;
    
    
    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = depotService.queryAll(qo);
        model.addAttribute("result", result);

        return "depot/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Depot entity = depotService.getById(id);
            model.addAttribute("entity", entity);
        }

        return "depot/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Depot entity){
        depotService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            depotService.deleteById(id);
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
