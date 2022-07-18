package br.com.beta.dublybank.service;

import br.com.beta.dublybank.enums.StatusEmprestimo;
import br.com.beta.dublybank.model.Emprestimo;
import br.com.beta.dublybank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    public Boolean validarLimiteEmprestimos(User user) {
        // Retorna 'true' se o User não ultrapassou o limite de 3 emprestimos pendentes.
        // Retorna 'false' se o User ultrapassou o limite de 3 emprestimos pendentes.
        Integer limiteEmprestimo = 0;
        for (Emprestimo emprestimo : user.getConta().getEmprestimos()) {
            if (emprestimo.getStatus().equals(StatusEmprestimo.PENDENTE)) {
                limiteEmprestimo++;
            }
        }
        if (limiteEmprestimo >= 3) {
            return false;
        } else {
            return true;
        }
    }








}
