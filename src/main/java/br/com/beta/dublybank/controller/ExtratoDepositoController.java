package br.com.beta.dublybank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("extratoDeposito")
public class ExtratoDepositoController {

    @RequestMapping
    public String extratoDeposito(){
        return "extrato/extratoDeposito.html";
    }

}
