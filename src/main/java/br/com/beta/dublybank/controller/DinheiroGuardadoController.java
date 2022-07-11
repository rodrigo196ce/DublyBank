package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.OperacaoGuardarDinheiroDto;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.ContaService;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("dinheiroGuardado")
public class DinheiroGuardadoController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContaService contaService;

    @RequestMapping
    public String dinheiroGuardado(Model model){
        User user = this.userService.findUserAndConta();
        model.addAttribute("user",user);
        return "conta/dinheiroGuardadoHome.html";
    }

    @RequestMapping("resgatar")
    public String resgatar(Model model, OperacaoGuardarDinheiroDto operacaoGuardarDinheiroDto){
        model.addAttribute("user",this.userService.findUserAndConta());
        return "conta/resgatar.html";
    }

    @RequestMapping("guardar")
    public String guardar(Model model, OperacaoGuardarDinheiroDto operacaoGuardarDinheiroDto){
        model.addAttribute("user",this.userService.findUserAndConta());
        return "conta/guardar.html";
    }

    @RequestMapping("operacao")
    public String operacao(@Valid OperacaoGuardarDinheiroDto operacaoGuardarDinheiroDto, BindingResult result,
                           Model model, HttpServletRequest request){
        User user = this.userService.findUserAndConta();
        model.addAttribute("user",user);
        if(request.getParameter("tipo").equals("guardar")){
            if(result.hasErrors()){
                return "conta/guardar.html";
            }
           Double valorDouble = Double.valueOf(operacaoGuardarDinheiroDto.getValor());
            BigDecimal valorBigDecimal = BigDecimal.valueOf(valorDouble);
            if(valorBigDecimal.compareTo(user.getConta().getSaldo())==1){
                // O valorBigDecimal é maior que o saldo da conta.
                model.addAttribute("error",
                        "O valor inserido é maior que o Saldo disponível.");
                return "conta/guardar.html";
            }
            this.contaService.guardarDinheiro(operacaoGuardarDinheiroDto.getValor(),user);
            model.addAttribute("mensagemSucesso","Dinheiro guardado com sucesso.");
            return "conta/guardar.html";
        }else if(request.getParameter("tipo").equals("resgatar")){
            if(result.hasErrors()){
                return "conta/resgatar.html";
            }
            Double valorDouble = Double.valueOf(operacaoGuardarDinheiroDto.getValor());
            BigDecimal valorBigDecimal = BigDecimal.valueOf(valorDouble);
            if(valorBigDecimal.compareTo(user.getConta().getDinheiroGuardado())==1){
                // O valorBigDecimal é maior que o dinheiro guardado.
                model.addAttribute("error",
                        "O valor inserido é maior que o Total guardado.");
                return "conta/resgatar.html";
            }
            this.contaService.resgatarDinheiro(operacaoGuardarDinheiroDto.getValor(),user);
            model.addAttribute("mensagemSucesso","Dinheiro resgatado com sucesso.");
            return "conta/resgatar.html";
        }
        return null;
    }

}
