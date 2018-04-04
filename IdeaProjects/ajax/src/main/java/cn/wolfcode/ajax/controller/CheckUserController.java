package cn.wolfcode.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("ajax")
public class CheckUserController {

    @RequestMapping("checkUser")
    public ModelAndView checkUser(String username, HttpServletResponse resp) throws IOException {
        //设置响应数据的MIME类型
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //检查用户传入的参数---做相应的操作
        if("admin".equals(username) || "root".equals(username)){
            out.print("该用户已存在");
        }else{
            out.print("该用户可以注册");
        }
        return null;
    }
}
