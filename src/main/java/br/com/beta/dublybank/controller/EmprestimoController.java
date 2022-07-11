package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.SimularEmprestimoDto;
import br.com.beta.dublybank.model.Emprestimo;
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
    DublyUtil dublyUtil;

    @Autowired
    private ContaService contaService;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String emprestimo(){
      return"emprestimo/emprestimoHome.html";
    }

    @RequestMapping("telaSimular")
    public String solicitar(SimularEmprestimoDto simularEmprestimoDto){
        return "emprestimo/telaSimular.html";
    }

    @RequestMapping("simularSolicitacao")
    public String simularSolicitacao(@Valid SimularEmprestimoDto simularEmprestimoDto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "emprestimo/telaSimular.html";
        }
        Boolean resultValidDate = this.dublyUtil.validarDataBefore(simularEmprestimoDto.getData());
        if(resultValidDate){
            model.addAttribute("errorDate","A data não pode ser inferior a data atual.");
            return "emprestimo/telaSolicitar.html";
        }
        Emprestimo emprestimo = this.contaService.emprestimo(
                this.userService.findUserAndConta(),
                simularEmprestimoDto.getValor(),
                simularEmprestimoDto.getData());

        model.addAttribute("emprestimo",emprestimo);
        model.addAttribute("dataSolicitacao", LocalDate.now());
        return "emprestimo/resultadoSimulacao.html";
    }

}
