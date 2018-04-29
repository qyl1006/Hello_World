package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockOutComeBill")
public class StockOutcomeBillController {
    @Autowired
    private IStockOutcomeBillService stockOutComeBillService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IClientService clientService;



    @RequestMapping("list")
    public String list(@ModelAttribute("qo") StockOutcomeBillQueryObject qo, Model model){
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
            StockOutcomeBill entity = stockOutComeBillService.getById(id);
            model.addAttribute("entity",entity);
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
            StockOutcomeBill entity = stockOutComeBillService.getById(id);
            model.addAttribute("entity",entity);
        }

        //供应商
        model.addAttribute("depots", depotService.listAll());

        //客户
        model.addAttribute("clients", clientService.listAll());
        return "stockOutComeBill/view";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(StockOutcomeBill entity){
        stockOutComeBillService.insertOrUpdate(entity);

        return "redirect:/stockOutComeBill/list.do";
    }

    @RequestMapping("auditor")
    @ResponseBody
    public Object auditor(Long id){
        JSONResult json = new JSONResult();
        if (id != null) {

            try {
                stockOutComeBillService.updataAuditorById(id);
            } catch (Exception e) {
                e.printStackTrace();
                json.mark(e.getMessage());
            }
        }

        return json;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            stockOutComeBillService.deleteById(id);
        }

        return new JSONResult();
    }

}
