package br.com.beta.dublybank.service;

import br.com.beta.dublybank.dto.PrePagamentoSimulacaoEmprestimoDto;
import br.com.beta.dublybank.enums.StatusEmprestimo;
import br.com.beta.dublybank.model.*;
import br.com.beta.dublybank.repository.ContaRepository;
import br.com.beta.dublybank.repository.DepositoRepository;
import br.com.beta.dublybank.repository.EmprestimoRepository;
import br.com.beta.dublybank.repository.TransferenciaPixRepository;
import br.com.beta.dublybank.util.DublyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
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

    @Autowired
    private EmprestimoService emprestimoService;

    Random random = new Random();

    public Conta save(User user){
        Conta conta = this.contaRepository
                .save(new Conta(random.nextInt(10000),random.nextInt(10000),user));
        return conta;
    }

    public void pagamento(BigDecimal valorPagamento,User user){
        user.getConta().setSaldo(user.getConta().getSaldo().subtract(valorPagamento));
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
    public Boolean validarSaldo(BigDecimal valor,User user){
        if(user.getConta().getSaldo().compareTo(valor)==1){
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
    public void simularEmprestimo(User user, String valorSolicitado, String data) {
        BigDecimal valorSolicitadoBd = this.dublyUtil.converterDoubleForBigDecimal(valorSolicitado);
        LocalDate dataLd = this.dublyUtil.converterStringForLocalDate(data);
        Emprestimo emprestimo = this.emprestimoRepository.save(new Emprestimo(valorSolicitadoBd, dataLd));
        if(user.getConta().getEmprestimoSimulacao() != null){
            this.emprestimoRepository.delete(user.getConta().getEmprestimoSimulacao());
        }
        user.getConta().setEmprestimoSimulacao(emprestimo);

        Period period = LocalDate.now().until(dataLd);
        for (int i = 0; i <= 1000000; i++) {
            if (period.toTotalMonths() == i) {
                emprestimo.setJuros((i * 3));
            }
        }
        emprestimo.setTotalMesesFinanciamento(period.toTotalMonths());
        double juros = (double) emprestimo.getJuros() / 100;
        BigDecimal jurosBd = BigDecimal.valueOf(juros);
        BigDecimal resultMultiply = emprestimo.getValorSolicitado().multiply(jurosBd);
        emprestimo.setTotalPagar(emprestimo.getValorSolicitado().add(resultMultiply));
    }

    @Transactional
    public Boolean realizarEmprestimo(User user){
        Boolean resultValidarLimiteEmprestimo = this.emprestimoService.validarLimiteEmprestimos(user);
        if(resultValidarLimiteEmprestimo==false){
            return false;
        }else{
            user.getConta().getEmprestimoSimulacao().setConta(user.getConta());
            user.getConta().getEmprestimoSimulacao().setStatus(StatusEmprestimo.PENDENTE);
            user.getConta().getEmprestimos().add(user.getConta().getEmprestimoSimulacao());
            user.getConta().setSaldo(user.getConta().getSaldo().add(user.getConta().getEmprestimoSimulacao().getValorSolicitado()));
            user.getConta().setEmprestimoSimulacao(null);
            return true;
        }
    }

    public PrePagamentoSimulacaoEmprestimoDto prePagamento(String idEmprestimo) {
        Emprestimo emprestimo = this.emprestimoRepository.findByIdE(Integer.valueOf(idEmprestimo));
        PrePagamentoSimulacaoEmprestimoDto ppse = this.emprestimoService.calcularPrePagamento(emprestimo);
        return ppse;
    }

    @Transactional
    public Boolean pagarEmprestimo(String valorSolicitado, String dataPagamento, String juros, String totalPagar, String totalMesesFinanciamento,
                                   String idEmprestimo, User user) {

        PrePagamentoSimulacaoEmprestimoDto ppse = this.emprestimoService.tppse(valorSolicitado, dataPagamento, juros, totalPagar,
                totalMesesFinanciamento, idEmprestimo);

        Emprestimo emprestimo = this.emprestimoRepository.findByIdE(ppse.getIdEmprestimo());

        Boolean saldoValid = this.validarSaldo(emprestimo.getTotalPagar(), user);
        if (saldoValid) {
            this.pagamento(emprestimo.getTotalPagar(), user);
            emprestimo.setDataPagamento(ppse.getDataPagamento());
            emprestimo.setJuros(ppse.getJuros());
            emprestimo.setTotalPagar(ppse.getTotalPagar());
            emprestimo.setTotalMesesFinanciamento(ppse.getTotalMesesFinanciamento());
            emprestimo.setStatus(StatusEmprestimo.FINALIZADO);
            return true;
        } else {
            return false;
        }
    }







}
