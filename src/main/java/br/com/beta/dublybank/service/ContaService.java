package br.com.beta.dublybank.service;

import br.com.beta.dublybank.model.*;
import br.com.beta.dublybank.repository.ContaRepository;
import br.com.beta.dublybank.repository.DepositoRepository;
import br.com.beta.dublybank.repository.EmprestimoRepository;
import br.com.beta.dublybank.repository.TransferenciaPixRepository;
import br.com.beta.dublybank.util.DublyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private DublyUtil dublyUtil;

    @Autowired
    private TransferenciaPixRepository transferenciaPixRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    Random random = new Random();

    public Conta save(User user){
        Conta conta = this.contaRepository
                .save(new Conta(random.nextInt(10000),random.nextInt(10000),user));
        return conta;
    }

    @Transactional
    public Deposito depositar(String valor,User user){
        Double valor$ = Double.valueOf(valor);
        BigDecimal valorBigDecimal = BigDecimal.valueOf(valor$);
        user.getConta().setSaldo(user.getConta().getSaldo().add(valorBigDecimal));
        Deposito deposito = this.depositoRepository
                .save(new Deposito(valorBigDecimal, LocalDate.now(),user.getConta()));
        user.getConta().getDepositos().add(deposito);
        return deposito;
    }
    @Transactional
    public void guardarDinheiro(String valor,User user){
        Double valor$ = Double.valueOf(valor);
        BigDecimal valorBigDecimal = BigDecimal.valueOf(valor$);
        user.getConta().setSaldo(user.getConta().getSaldo().subtract(valorBigDecimal));
        user.getConta().setDinheiroGuardado(user.getConta().getDinheiroGuardado().add(valorBigDecimal));
    }
    @Transactional
    public void resgatarDinheiro(String valor, User user){
        Double valor$ = Double.valueOf(valor);
        BigDecimal valorBigDecimal = BigDecimal.valueOf(valor$);
        user.getConta().setDinheiroGuardado(user.getConta().getDinheiroGuardado().subtract(valorBigDecimal));
        user.getConta().setSaldo(user.getConta().getSaldo().add(valorBigDecimal));
    }

    @Transactional
    public void cadastrarChavePixCpf(User user){
        user.getConta().setChavePixCpfRegistrada(true);
    }

    @Transactional
    public void cadastrarChavePixEmail(User user){
        user.getConta().setChavePixEmailRegistrada(true);
    }

    @Transactional
    public void cadastrarChavePixAleatoria(User user){
        Random random = new Random();
        int valores = random.nextInt();
        user.getConta().setChavePixAleatoria(String.valueOf(valores));
    }

    @Transactional
    public void removerChave(String tipo,User user){
        if(tipo.equals("cpf")){
            user.getConta().setChavePixCpfRegistrada(null);
        }else if(tipo.equals("email")){
            user.getConta().setChavePixEmailRegistrada(null);
        }else if(tipo.equals(("chaveA"))){
            user.getConta().setChavePixAleatoria(null);
        }
    }

    @Transactional
    public void definirLimitePix(String valor,User user){
        BigDecimal valorB = this.dublyUtil.converterDoubleForBigDecimal(valor);
        user.getConta().setValorLimitePix(valorB);
    }

    public Boolean validarLimitePix(String valor,User user){
        BigDecimal valorB = this.dublyUtil.converterDoubleForBigDecimal(valor);
        if(valorB.compareTo(user.getConta().getValorLimitePix())==1){
            return true;
        }else{
            return false;
        }
    }

    public Boolean validarSaldo(String valor,User user){
       BigDecimal valorBigDecimal = this.dublyUtil.converterDoubleForBigDecimal(valor);
       if(user.getConta().getSaldo().compareTo(valorBigDecimal)==1){
           return true;
       }else{
           return false;
       }
    }

    @Transactional
    public void transferenciaPix(User user, User userDestinatario, String valor){
        BigDecimal valorBigDecimal = this.dublyUtil.converterDoubleForBigDecimal(valor);
        TransferenciaPix transferenciaPix = new TransferenciaPix(
                valorBigDecimal, LocalDate.now(),user.getConta(),userDestinatario.getNome()
                ,userDestinatario.getConta().getAgencia(),userDestinatario.getConta().getConta());

        user.getConta().getTransferenciasPix().add(this.transferenciaPixRepository.save(transferenciaPix));
        user.getConta().setSaldo(user.getConta().getSaldo().subtract(valorBigDecimal));
        userDestinatario.getConta().setSaldo(userDestinatario.getConta().getSaldo().add(valorBigDecimal));
    }

    @Transactional
    public Emprestimo emprestimo(User user,String valorSolicitado,String dataPagamento){

        BigDecimal valorSolicitadoBd = this.dublyUtil.converterDoubleForBigDecimal(valorSolicitado);
        LocalDate dataPagamentoLd = this.dublyUtil.converterStringForLocalDate(dataPagamento);
        Emprestimo emprestimo = new Emprestimo(valorSolicitadoBd,dataPagamentoLd,user.getConta());
        user.getConta().getEmprestimos().add(this.emprestimoRepository.save(emprestimo));

        Period period = LocalDate.now().until(dataPagamentoLd);
        for(int i=0;i<=100;i++){
            if(period.toTotalMonths()==i){
                emprestimo.setJuros((i*3));
            }
        }
        emprestimo.setTotalMesesFinanciamento(period.toTotalMonths());

        return emprestimo;
    }






}
