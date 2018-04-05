package cn.wolfcode.ajax.controller;

import cn.wolfcode.ajax.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("json")
public class JSONController {

    /*
      @ResponseBody 告诉SpringMVC不要跳转视图
      直接把返回的对象转为JSON,并且响应
     */
    @ResponseBody
    @RequestMapping("jackson")
    public Employee testJackson(){
        Employee entity = new Employee();
        entity.setUsername("师姐");
        entity.setAge(22);
        entity.setEmail("yuelinshi@qq.com");
        entity.setId(1L);

        List<String> arr = new ArrayList<>();
        arr.add("Java");
        arr.add("JavaScript");
        arr.add("gril");
        entity.setHobby(arr);

        return entity;
    }
}
