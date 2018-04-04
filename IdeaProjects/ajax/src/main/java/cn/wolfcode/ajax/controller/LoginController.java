package cn.wolfcode.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("ajax")
public class LoginController {

    @RequestMapping("login")
    public ModelAndView login(String username, HttpServletResponse resp) throws IOException {
        //设置响应数据的MIME类型
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        System.out.println(username);
        //模拟登陆
        if("admin".equals(username)){
            out.print("OK");
        }else {
            out.print("账号或密码不匹配2222");
        }

        return null;
    }
}
