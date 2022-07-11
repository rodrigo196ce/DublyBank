package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("minhaConta")
public class MinhaContaController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String minhaConta(Model model){
        model.addAttribute("user",this.userService.findUserAndConta());
        return"minhaConta/minhaConta.html";
    }

}
