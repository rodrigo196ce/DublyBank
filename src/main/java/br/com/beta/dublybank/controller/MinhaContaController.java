package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.EditarDto;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping("editar")
    public String editar(EditarDto editarDto){
        editarDto.toUser(this.userService.findUser());
        return "minhaConta/editar.html";
    }

    @PostMapping("realizarEdicao")
    public String realizarEdicao(Model model, @Valid EditarDto editarDto, BindingResult result){
        if(result.hasErrors()){
            return "minhaConta/editar.html";
        }
        this.userService.editar(editarDto);
        return "redirect:/minhaConta/mensagemSuccess";
    }

    @GetMapping("mensagemSuccess")
    public ModelAndView mensagemSuccess(ModelAndView modelAndView){
        modelAndView.setViewName("minhaConta/minhaConta.html");
        modelAndView.addObject("user",this.userService.findUserAndConta());
        modelAndView.addObject("mensagem","Edição realizada com sucesso.");
        return  modelAndView;
    }

}
