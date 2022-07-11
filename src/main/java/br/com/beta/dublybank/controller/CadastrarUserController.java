package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.CadastroUserDto;
import br.com.beta.dublybank.repository.UserRepository;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("cadastrar")
public class CadastrarUserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String cadastrar(CadastroUserDto cadastroUserDto) {
        return "inicial/cadastrar.html";
    }

    @PostMapping("novo")
    public String novo(@Valid CadastroUserDto cadastroUserDto, BindingResult result) {
        if(result.hasErrors()){
            return "inicial/cadastrar.html";
        }
        this.userService.save(cadastroUserDto.toUser());
        return "forward:/login";
    }

}

