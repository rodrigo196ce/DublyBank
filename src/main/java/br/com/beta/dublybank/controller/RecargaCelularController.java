package br.com.beta.dublybank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recargaCelular")
public class RecargaCelularController {

    @RequestMapping()
    public String recargaCelular(){
        return "recargaCelular/recargaCelularInicial.html";
    }

}
