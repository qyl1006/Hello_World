package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.JSONResult;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;


//    @RequestMapping("login")
//    @ResponseBody
//    public Object login(String username, String password){
//        JSONResult json = new JSONResult();
//        try {
//
//            employeeService.login(username, password);
//        }catch (Exception e){
//            e.printStackTrace();
//            json.mark(e.getMessage());
//        }
//        return json;
//    }
//
    @RequestMapping("login")
    public String login(HttpServletRequest req, Model model){
        //取出登陆失败抛的异常
        Object shiroLoginFailure = req.getAttribute("shiroLoginFailure");
        if(shiroLoginFailure != null){
            if(UnknownAccountException.class.getName().equals(shiroLoginFailure)){
                model.addAttribute("errorMsg", "用户名不存在");
            }else if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
                model.addAttribute("errorMsg", "密码不正确");
            }
        }

        return "forward:/login.jsp";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }

//    @RequestMapping("logout")
//    public String logout(HttpSession session){
//        session.invalidate();
//
//        return "redirect:/login.jsp";
//    }

    }
