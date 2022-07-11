package br.com.beta.dublybank.controller;

import br.com.beta.dublybank.dto.TransferenciaPixDto;
import br.com.beta.dublybank.dto.ValorLimitePix;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.ContaService;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("areaPix")
public class AreaPixController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContaService contaService;

    @RequestMapping
    public String areaPix() {
        return "areaPix/areaPixHome.html";
    }

    @RequestMapping("registrarChaves")
    public String registrarChaves(Model model) {
        model.addAttribute("user", this.userService.findUserAndConta());
        return "areaPix/registrarChaves.html";
    }

    @RequestMapping("cadastrarChaves")
    public String cadastrarChaves(HttpServletRequest request) {
        request.setAttribute("user", this.userService.findUserAndConta());
        User user = this.userService.findUserAndConta();
        if (request.getParameter("tipo").equals("cpf")) {
            this.contaService.cadastrarChavePixCpf(user);
            request.setAttribute("mensagem", "Chave Cpf cadastrada com sucesso.");
            return "areaPix/registrarChaves.html";
        } else if (request.getParameter("tipo").equals("email")) {
            this.contaService.cadastrarChavePixEmail(user);
            request.setAttribute("mensagem", "Chave E-mail cadastrada com sucesso.");
            return "areaPix/registrarChaves.html";
        } else if (request.getParameter("tipo").equals("chaveAleatoria")) {
            this.contaService.cadastrarChavePixAleatoria(user);
            request.setAttribute("mensagem", "Chave aleatória cadastrada com sucesso.");
            return "areaPix/registrarChaves.html";
        }
        return null;
    }

    @RequestMapping("configurarPix")
    public String configurarPix() {
        return "areaPix/configurarPixHome.html";
    }

    @RequestMapping("minhasChavesPix")
    public String minhasChavesPix(Model model) {
        model.addAttribute("user", this.userService.findUserAndConta());
        return "areaPix/minhasChavesPix.html";
    }

    @RequestMapping("removerChave")
    public String removerChave(HttpServletRequest request) {
        this.contaService.removerChave(request.getParameter("tipo"), this.userService.findUserAndConta());

        request.setAttribute("mensagem", "Chave pix removida com sucesso.");
        request.setAttribute("user", this.userService.findUserAndConta());
        return "areaPix/minhasChavesPix.html";
    }

    @RequestMapping("limitePix")
    public String limitePix(ValorLimitePix valorLimitePix, Model model) {
        model.addAttribute("user", this.userService.findUserAndConta());
        return "areaPix/limitePix.html";
    }

    @RequestMapping("ajustarLimitePix")
    public String ajustarLimitePix(@Valid ValorLimitePix valorLimitePix, BindingResult result, Model model) {
        model.addAttribute("user", this.userService.findUserAndConta());
        if (result.hasErrors()) {
            return "areaPix/limitePix.html";
        }
        this.contaService.definirLimitePix(valorLimitePix.getValor(), this.userService.findUserAndConta());
        model.addAttribute("mensagem", "Limite definido com sucesso.");
        return "areaPix/limitePix.html";
    }

    @RequestMapping("transferir")
    public String transferir(Model model, TransferenciaPixDto transferenciaPixDto) {
        model.addAttribute("user", this.userService.findUserAndConta());
        return "areaPix/transferir.html";
    }

    @RequestMapping("transferencia01")
    public String transferencia01(@Valid TransferenciaPixDto transferenciaPixDto, BindingResult result,
                                  Model model, HttpServletRequest request) {
        model.addAttribute("user", this.userService.findUserAndConta());
        if (result.hasErrors()) {
            return "areaPix/transferir.html";
        }
        Boolean validado = this.contaService
                .validarSaldo(transferenciaPixDto.getValor(), this.userService.findUserAndConta());
        if (validado == false) {
            model.addAttribute("error", "Saldo insuficiente.");
            return "areaPix/transferir.html";
        }
        User userDestinatario = this.userService.findUserContainChavePix(transferenciaPixDto.getChave());
        if (userDestinatario == null) {
            model.addAttribute("error", "Chave pix não encontrada.");
        } else {
            Boolean limiteAtingido = this.contaService
                    .validarLimitePix(transferenciaPixDto.getValor(), this.userService.findUserAndConta());
            if (limiteAtingido) {
                model.addAttribute("error", "Limite Pix atingido.");
                return "areaPix/transferir.html";
            }

            this.contaService
                    .transferenciaPix(this.userService.findUserAndConta(), userDestinatario, transferenciaPixDto.getValor());
            model.addAttribute("valor", transferenciaPixDto.getValor());
            model.addAttribute("data", LocalDate.now());
            model.addAttribute("user", this.userService.findUserAndConta());
            model.addAttribute("userDestinatario", userDestinatario);
            return "extrato/extratoTransferenciaPix.html";
        }
        return "areaPix/transferir.html";
    }


}
