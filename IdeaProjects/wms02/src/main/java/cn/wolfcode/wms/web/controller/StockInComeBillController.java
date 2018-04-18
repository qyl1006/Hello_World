package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.query.StockInComeBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockInComeBillService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockIncomeBill")
public class StockInComeBillController {
    @Autowired
    private IStockInComeBillService stockIncomeBillService;
    @Autowired
    private IDepotService depotService;



    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StockInComeBillQueryObject qo, Model model){
        PageResult result = stockIncomeBillService.queryAll(qo);
        model.addAttribute("result", result);

        //供应商
        model.addAttribute("depots", depotService.listAll());
        return "stockIncomeBill/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            StockInComeBill entity = stockIncomeBillService.getById(id);
            model.addAttribute("entity",entity);
        }

        //供应商
        model.addAttribute("depots", depotService.listAll());
        return "stockIncomeBill/input";
    }

    @RequestMapping("view")
    public String view(Long id, Model model){
        if (id != null) {
            StockInComeBill entity = stockIncomeBillService.getById(id);
            model.addAttribute("entity",entity);
        }

        //供应商
        model.addAttribute("depots", depotService.listAll());
        return "stockIncomeBill/view";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(StockInComeBill entity){
        stockIncomeBillService.insertOrUpdate(entity);

        return "redirect:/stockIncomeBill/list.do";
    }

    @RequestMapping("auditor")
    public String auditor(Long id){
        if (id != null) {

            stockIncomeBillService.updataAuditorById(id);
        }

        return "redirect:/stockIncomeBill/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            stockIncomeBillService.deleteById(id);
        }

        return new JSONResult();
    }

}
