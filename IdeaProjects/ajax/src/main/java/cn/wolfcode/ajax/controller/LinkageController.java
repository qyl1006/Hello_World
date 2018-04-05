package cn.wolfcode.ajax.controller;

import cn.wolfcode.ajax.linkage.City;
import cn.wolfcode.ajax.linkage.Province;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("linkage")
public class LinkageController {

    //Java对象转为HTML方式 最后使用JSON方式
    //<option value="xx">xx</option>

    @ResponseBody
    @RequestMapping("getProvinces")
    public Object getProvinces(){
//        //获取所有省份数据
//        List<Province> ps = Province.getProvinces();
//        StringBuilder sb = new StringBuilder(80);
//
//        //拼接HTML
//        for (Province p : ps) {
//            sb.append("<option value=\"").append(p.getId())
//                    .append("\">").append(p.getName()).append("</option>");
//
//        }
//
//        //响应输出
//        resp.setContentType("text/html; charset=utf-8");

        return Province.getProvinces();
    }


    //Java对象转为HTML方式  最后JSON方式
    //<option value="x">xx</option>
    @ResponseBody
    @RequestMapping("getCity")
    public Object getCity(Long id){
//        //获取所有省份数据
//        List<City> cs = City.getCitiesByProvinceId(id);
//        StringBuilder sb = new StringBuilder(80);
//
//        //拼接HTML
//        for (City c : cs) {
//            sb.append("<option value=\"").append(c.getId())
//                    .append("\">").append(c.getName()).append("</option>");
//
//        }
//        System.out.println(sb);
//
//        //响应输出
//        resp.setContentType("text/html; charset=utf-8");
        return City.getCitiesByProvinceId(id);
    }
}
