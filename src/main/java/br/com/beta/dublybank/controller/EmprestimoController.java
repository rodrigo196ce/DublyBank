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


}
