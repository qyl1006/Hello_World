package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @RequestMapping("list")
    public String list(QueryObject qo, Model model){
        PageResult result = clientService.queryAll(qo);
        model.addAttribute("result", result);

        return "client/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Client entity = clientService.getById(id);
            model.addAttribute("entity",entity);
        }

        return "client/input";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Client entity){
        clientService.insertOrUpdate(entity);

        return "redirect:/client/list.do";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id != null) {
            clientService.deleteById(id);
        }

        return new JSONResult();
    }

}
