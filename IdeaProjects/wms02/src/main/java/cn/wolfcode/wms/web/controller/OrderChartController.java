package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.OrderSaleQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.*;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chart")
public class OrderChartController {
    @Autowired
    private IOrderChartService orderChartService;
    //供应商
    @Autowired
    private ISupplierService supplierService;
    //品牌
    @Autowired
    private IBrandService brandService;
    //客户
    @Autowired
    private IClientService clientService;

    @RequestMapping("order")
    public String list(@ModelAttribute("qo") OrderChartQueryObject qo, Model model){
        List<Map<String, Object>> maps = orderChartService.queryOrderChart(qo);
        model.addAttribute("list", maps);

        //供应商
        model.addAttribute("suppliers", supplierService.listAll());

        //品牌
        model.addAttribute("brands", brandService.listAll());

        return "chart/order";
    }


    @RequestMapping("sale")
    public String saleList(@ModelAttribute("qo") OrderSaleQueryObject qo, Model model){
        List<Map<String, Object>> maps = orderChartService.queryOrderSale(qo);
        model.addAttribute("list", maps);

        //客户
        model.addAttribute("clients", clientService.listAll());

        //品牌
        model.addAttribute("brands", brandService.listAll());

        return "chart/sale";
    }
}
