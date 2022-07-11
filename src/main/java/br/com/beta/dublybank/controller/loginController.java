package br.com.beta.dublybank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class loginController {

    @RequestMapping
    public String login(){
        return "inicial/login.html";
    }

}
