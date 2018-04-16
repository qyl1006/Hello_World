package cn.wolfcode.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("jquery")
public class echoJQueryController {
    @RequestMapping("echo")
    @ResponseBody
    public Object echo(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 4L);
        return map;
    }
}
