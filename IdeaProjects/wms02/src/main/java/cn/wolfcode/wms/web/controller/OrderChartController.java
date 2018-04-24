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
import cn.wolfcode.wms.util.JSONUtil;
import cn.wolfcode.wms.util.MyDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
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


    @RequestMapping("saleByBar")
    public String saleByBar(@ModelAttribute("qo") OrderSaleQueryObject qo, Model model){

        List<Object> x = new ArrayList<>();
        List<Object> y = new ArrayList<>();

        List<Map<String, Object>> maps = orderChartService.queryOrderSale(qo);
        for (Map<String, Object> map : maps) {
            //取出当前行中的groupType和totalAmount两个值对应存入x/y
            x.add(map.get("groupType"));
            y.add(map.get("amount"));
        }

        //转换成json存入模式对象中
        model.addAttribute("groupType", MyDictionary.SALE_MAP.get(qo.getGroupType()));
        model.addAttribute("x", JSONUtil.toJSONString(x));
        model.addAttribute("y", JSONUtil.toJSONString(y));

        return "chart/saleByBar";
    }

    @RequestMapping("saleByPie")
    public String saleByPie(@ModelAttribute("qo") OrderSaleQueryObject qo, Model model){

        List<Object> x = new ArrayList<>();
        List<Map<Object,Object>> list = new ArrayList<>();

        Map<Object, Object> mapList = new HashMap();


        List<Map<String, Object>> maps = orderChartService.queryOrderSale(qo);
        for (Map<String, Object> map : maps) {

//            Map<Object, Object> mapList = new HashMap<>();
            //取出当前行中的groupType和totalAmount两个值对应存入x/y
//            x.add(map.get("groupType"));
//            mapList.put( map.get("amount"), map.get("groupType"));
//
//            list.add(mapList);
      }


        /**
         * [
         {value:335, name:'admin'},
         {value:310, name:'李四明'},
         ]
         */
            mapList.put(335, "admin");
            list.add(mapList);


        //转换成json存入模式对象中
        model.addAttribute("groupType", MyDictionary.SALE_MAP.get(qo.getGroupType()));
        model.addAttribute("x", JSONUtil.toJSONString(x));
        model.addAttribute("list", JSONUtil.toJSONString(list));

        return "chart/saleByPie";
    }
}
