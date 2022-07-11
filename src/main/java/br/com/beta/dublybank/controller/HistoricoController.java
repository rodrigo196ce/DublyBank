package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.model.Deposito;
import br.com.beta.dublybank.model.TransferenciaPix;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.HistoricoService;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("historico")
public class HistoricoController {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoricoService historicoService;

    @RequestMapping
    public String historico(){
        return "historico/historicoHome.html";
    }

    @RequestMapping("deposito")
    public String deposito(Model model){
        List<Deposito> depositos = this.historicoService.findDepositoByUsername();
        model.addAttribute("deposito",depositos);
        return "historico/historicoHome.html";
    }

    @RequestMapping("transferencia")
    public String transferencia(Model model){
        List<TransferenciaPix> transferencias = this.historicoService.findTransferenciaUsername();
        model.addAttribute("transferencia",transferencias);
        return "historico/historicoHome.html";
    }

}
