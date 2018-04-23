package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("productStock")
public class ProductStockController {
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;
    
    
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ProductStockQueryObject qo, Model model){
        qo.setPageSize(20);
        PageResult result = productStockService.queryAll(qo);
        model.addAttribute("result", result);

        model.addAttribute("depots", depotService.listAll());
        model.addAttribute("brands", brandService.listAll());

        return "productStock/list";
    }
}
