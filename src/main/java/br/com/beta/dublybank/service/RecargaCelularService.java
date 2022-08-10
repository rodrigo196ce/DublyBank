package br.com.beta.dublybank.service;

import br.com.beta.dublybank.model.Conta;
import br.com.beta.dublybank.model.RecargaCelular;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.repository.RecargaCelularRepository;
import br.com.beta.dublybank.util.DublyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class RecargaCelularService {

    @Autowired
    private UserService userService;

    @Autowired
    private RecargaCelularRepository recargaCelularRepository;

    @Autowired
    private DublyUtil dublyUtil;

    public Boolean validarNumeroTelefone(String telefone){
        User user = userService.findValueTelefone(telefone);
        if(user==null){
            return false;
        }else{
            return true;
        }
    }

    public Boolean validarSaldo(User user,String valorRecarga){
        BigDecimal valorBd = this.dublyUtil.converterDoubleForBigDecimal(valorRecarga);
        if(user.getConta().getSaldo().compareTo(valorBd)==1){
            return true;
            // Retorna true se o saldo da conta for superior que o valor da recarga.
        }else{
            return false;
            // Retorna false se o saldo da conta for inferior que o valor da recarga.
        }
    }

    @Transactional
    public RecargaCelular realizarRecarga(User user, String numero, String valorRecarga) {
        RecargaCelular recargaCelular = this.recargaCelularRepository.save(new RecargaCelular(user.getConta(), numero,
                dublyUtil.converterDoubleForBigDecimal(valorRecarga)));
        user.getConta().getRecargasCelular().add(recargaCelular);
        BigDecimal novoSaldo = user.getConta().getSaldo().subtract(this.dublyUtil.converterDoubleForBigDecimal(valorRecarga));
        user.getConta().setSaldo(novoSaldo);
        return recargaCelular;
    }














}
