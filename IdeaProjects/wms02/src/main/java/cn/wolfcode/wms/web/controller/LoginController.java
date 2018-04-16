package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;


    @RequestMapping("login")
    @ResponseBody
    public Object login(String username, String password){
        JSONResult json = new JSONResult();
        try {

            employeeService.login(username, password);
        }catch (Exception e){
            e.printStackTrace();
            json.mark(e.getMessage());
        }
        return json;
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/login.jsp";
    }

    }
