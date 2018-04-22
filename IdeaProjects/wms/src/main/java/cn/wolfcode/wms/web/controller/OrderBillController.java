package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orderBill")
public class OrderBillController {
    @Autowired
    private IOrderBillService orderBillService;
    //供应商
    @Autowired
    private ISupplierService supplierService;
    
    
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") OrderBillQueryObject qo, Model model){
        PageResult result = orderBillService.queryAll(qo);
        model.addAttribute("result", result);


        //供应商
        model.addAttribute("suppliers", supplierService.listAll());
        return "orderBill/list";
    }


    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            OrderBill entity = orderBillService.getById(id);
            model.addAttribute("entity", entity);
        }

        return "orderBill/input";
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(OrderBill entity){
        orderBillService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            orderBillService.deleteById(id);
        }

        return  new JSONResult();
    }


}
