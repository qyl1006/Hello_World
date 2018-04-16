package cn.wolfcode.ajax.controller;

import cn.wolfcode.ajax.linkage.City;
import cn.wolfcode.ajax.linkage.Province;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("linkage2")
public class LinkageController02 {


    @ResponseBody
    @RequestMapping("getProvinces")
    public Object getProvinces(){

        return Province.getProvinces();
    }

    @ResponseBody
    @RequestMapping("getCitys")
    public Object getCitys(Long id){

        return City.getCitiesByProvinceId(id);
    }


}
