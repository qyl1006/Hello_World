package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;

    //登陆检查
    @RequestMapping("login")
    public String login(String username, String password, Model model) throws Exception{
        try {
            employeeService.login(username, password);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg", "请登陆");
            return "forward:/login.jsp";
        }
        return "redirect:/main.do";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }
}
