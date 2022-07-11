package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.ValidarIdentidadeDepositoDto;
import br.com.beta.dublybank.dto.ValorDepositoDto;
import br.com.beta.dublybank.model.Deposito;
import br.com.beta.dublybank.service.ContaService;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("deposito")
public class DepositoController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContaService contaService;

    @RequestMapping
    public String deposito(ValorDepositoDto valorDepositoDto){
        return "deposito/depositoHome.html";
    }

    @RequestMapping("valorDeposito")
    public String valorDeposito(@Valid ValorDepositoDto valorDepositoDto, BindingResult result,
                                Model model){
        if(result.hasErrors()){
            return "deposito/depositoHome.html";
        }
        Double valor = Double.valueOf(valorDepositoDto.getValor());
        if(valor<0){
            model.addAttribute("error","O valor não pode ser negativo.");
            return "deposito/depositoHome.html";
        }
        model.addAttribute("valor",valorDepositoDto.getValor());
        return "forward:/deposito/directValidarIdentidade";
    }

    @RequestMapping("directValidarIdentidade")
    public String directValidarIdentidade(ValidarIdentidadeDepositoDto validarIdentidadeDepositoDto){
        return "deposito/validarIdentidade.html";
    }

    @RequestMapping("validarIdentidade")
    public String validarIdentidade(@Valid ValidarIdentidadeDepositoDto validarIdentidadeDepositoDto,
                                    BindingResult result,Model model){
        if(result.hasErrors()){
            return "deposito/validarIdentidade.html";
        }
        Boolean validado = this.userService.validarIdentidade(validarIdentidadeDepositoDto.getSenha());
        if(validado==false){
            model.addAttribute("error","Senha invalida.");
            return "deposito/validarIdentidade.html";
        }
       Deposito deposito = this.contaService
               .depositar(validarIdentidadeDepositoDto.getValor(),this.userService.findUserAndConta());
        model.addAttribute("deposito",deposito);
        return "forward:/extratoDeposito";
    }






}
