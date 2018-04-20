package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;
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
    //仓库
    @Autowired
    private DepotMapper depotMapper;
    //品牌
    @Autowired
    private BrandMapper brandMapper;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ProductStockQueryObject qo, Model model){
        qo.setPageSize(20);
        PageResult result = productStockService.queryAll(qo);
        model.addAttribute("result", result);

        //仓库
        model.addAttribute("depots", depotMapper.listAll());
        //品牌
        model.addAttribute("brands", brandMapper.listAll());
        return "productStock/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            ProductStock entity = productStockService.getById(id);
            model.addAttribute("entity",entity);
        }

        return "productStock/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(ProductStock entity){
        productStockService.insertOrUpdate(entity);

        return "redirect:/productStock/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            productStockService.deleteById(id);
        }

        return new JSONResult();
    }

}
