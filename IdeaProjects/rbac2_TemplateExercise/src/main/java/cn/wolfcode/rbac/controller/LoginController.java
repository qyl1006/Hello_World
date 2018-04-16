package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//检查登陆
@Controller
public class LoginController {
    //用于查询数据库  员工账号信息验证
    @Autowired
    private IEmployeeService employeeService;


    @RequestMapping("login")
    public String login(String username, String password, Model model){
        try{

            employeeService.login(username, password);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","账号密码错误,请重新登陆");
            return "forward:/login.jsp";
        }
        return "redirect:/main.do";

    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }

}
