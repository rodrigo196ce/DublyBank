package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.SimularEmprestimoDto;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.ContaService;
import br.com.beta.dublybank.service.UserService;
import br.com.beta.dublybank.util.DublyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("emprestimo")
public class EmprestimoController {

    @Autowired
    private DublyUtil dublyUtil;

    @Autowired
    private ContaService contaService;

    @Autowired
    private UserService userService;

    @RequestMapping()
    public String emprestimo(){
        return "emprestimo/emprestimoHome.html";
    }

    @RequestMapping("telaDadosSimularEmprestimo")
    public String telaDadosSimularEmprestimo(SimularEmprestimoDto simularEmprestimoDto){
        return "emprestimo/telaDadosSimularEmprestimo.html";
    }

    @RequestMapping("simularEmprestimo")
    public String simularEmprestimo(@Valid SimularEmprestimoDto simularEmprestimoDto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "emprestimo/telaDadosSimularEmprestimo.html";
        }
        Boolean resultData = this.dublyUtil.validarDataBefore(simularEmprestimoDto.getData());
        if(resultData){
            model.addAttribute("errorData","Data inválida. Informe uma data superior a atual.");
            return "emprestimo/telaDadosSimularEmprestimo.html";
        }
        this.contaService.simularEmprestimo(this.userService.findUserAndConta(),simularEmprestimoDto.getValor(),
                simularEmprestimoDto.getData());
        model.addAttribute("user",this.userService.findUserAndConta());
        return "emprestimo/telaSimularEmprestimo.html";
    }

    @RequestMapping("realizarEmprestimo")
    public String realizarEmprestimo(Model model){
        User user = this.userService.findUserAndConta();
        Boolean result = this.contaService.realizarEmprestimo(user);
        model.addAttribute("user",user);
        if(result==false){
            model.addAttribute("errorLimite","Limite de empréstimo ultrapassado.");
            return "emprestimo/telaSimularEmprestimo.html";
        }else{
            return "extrato/extratoEmprestimo.html";
        }
    }

    @RequestMapping("meusEmprestimos")
    public String meusEmprestimos(Model model){
        model.addAttribute("user",this.userService.findUserAndConta());
        return "emprestimo/meusEmprestimos.html";
    }


}
