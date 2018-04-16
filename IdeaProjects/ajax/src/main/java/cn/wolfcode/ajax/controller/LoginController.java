package cn.wolfcode.ajax.controller;

import cn.wolfcode.ajax.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("ajax")
public class LoginController {

    @RequestMapping("login")
    @ResponseBody
    public Object login(String username, String password){
        JSONResult jsonResult = new JSONResult();
        if(!("admin".equals(username) && "123".equals(password))){

            jsonResult.mark("账号或密码错误");
        }

        return jsonResult;
    }
}
