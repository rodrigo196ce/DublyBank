package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.RecargaCelularDto;
import br.com.beta.dublybank.enums.ValoresRecargaCelular;
import br.com.beta.dublybank.model.RecargaCelular;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.RecargaCelularService;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("recargaCelular")
public class RecargaCelularController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecargaCelularService recargaCelularService;

    @RequestMapping()
    public String recargaCelular(RecargaCelularDto recargaCelularDto){
        return "recargaCelular/recargaCelularInicial.html";
    }

    @PostMapping("validarNumero")
    public String validarNumero(@Valid RecargaCelularDto recargaCelularDto, BindingResult result, Model model){

        if(result.hasErrors()){
            return "recargaCelular/recargaCelularInicial.html";
        }
        Boolean resultValidacao = recargaCelularService.validarNumeroTelefone(recargaCelularDto.getNumero());
        if(resultValidacao==false){
            model.addAttribute("errorTelefone","O telefone inserido não foi encontrado.");
            return "recargaCelular/recargaCelularInicial.html";
        }

        model.addAttribute("valores", ValoresRecargaCelular.values());
        model.addAttribute("numero",recargaCelularDto.getNumero());
        model.addAttribute("user",this.userService.findUserAndConta());
        return "recargaCelular/valores.html";
    }

    @RequestMapping("realizarRecarga")
    public String realizarRecarga(HttpServletRequest request){
        Boolean resultValidacao = this.recargaCelularService.validarSaldo(this.userService.findUserAndConta(),
                request.getParameter("valoresRecargaCelular"));
        if(resultValidacao==false){
            request.setAttribute("valores", ValoresRecargaCelular.values());
            request.setAttribute("numero",request.getParameter("numero"));
            request.setAttribute("user",this.userService.findUserAndConta());
            request.setAttribute("errorSaldo","Saldo insuficiente");
            return "recargaCelular/valores.html";
        }
        RecargaCelular recargaCelular = this.recargaCelularService.realizarRecarga(this.userService.findUserAndConta(),request.getParameter("numero"),
                request.getParameter("valoresRecargaCelular"));
        request.setAttribute("recarga",recargaCelular);
        return "extrato/extratoRecargaCelular.html";
    }

}
