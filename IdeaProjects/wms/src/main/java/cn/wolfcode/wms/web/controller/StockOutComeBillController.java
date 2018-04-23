package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutComeBillQueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.lStockOutComeBillService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockOutComeBill")
public class StockOutComeBillController {
    @Autowired
    private lStockOutComeBillService stockOutComeBillService;
    //仓库
    @Autowired
    private IDepotService depotService;
    //客户
    @Autowired
    private IClientService clientService;
    
    
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StockOutComeBillQueryObject qo, Model model){
        PageResult result = stockOutComeBillService.queryAll(qo);
        model.addAttribute("result", result);


        //供应商
        model.addAttribute("depots", depotService.listAll());
        //客户
        model.addAttribute("clients", clientService.listAll());
        return "stockOutComeBill/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            StockOutComeBill entity = stockOutComeBillService.getById(id);
            model.addAttribute("entity", entity);
        }

        //供应商
        model.addAttribute("depots", depotService.listAll());

        //客户
        model.addAttribute("clients", clientService.listAll());
        return "stockOutComeBill/input";
    }

    @RequestMapping("view")
    public String view(Long id, Model model){
        if (id != null) {
            StockOutComeBill entity = stockOutComeBillService.getById(id);
            model.addAttribute("entity", entity);
        }

        //供应商
        model.addAttribute("depots", depotService.listAll());
        return "stockOutComeBill/view";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StockOutComeBill entity){
        if (entity != null) {

            stockOutComeBillService.insertOrUpdate(entity);
        }

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            stockOutComeBillService.deleteById(id);
        }

        return  new JSONResult();
    }

    @RequestMapping("auditor")
    public String auditor(Long id){
        if (id != null) {

            stockOutComeBillService.updataAuditorById(id);
        }

        return "redirect:/stockOutComeBill/list.do";
    }

}
