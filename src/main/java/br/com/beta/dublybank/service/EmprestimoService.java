package br.com.beta.dublybank.service;

import br.com.beta.dublybank.dto.PrePagamentoSimulacaoEmprestimoDto;
import br.com.beta.dublybank.enums.StatusEmprestimo;
import br.com.beta.dublybank.model.Conta;
import br.com.beta.dublybank.model.Emprestimo;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.repository.EmprestimoRepository;
import br.com.beta.dublybank.util.DublyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    DublyUtil dublyUtil;

    @Autowired
    EmprestimoRepository emprestimoRepository;

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


    public PrePagamentoSimulacaoEmprestimoDto calcularPrePagamento(Emprestimo emprestimo) {
        PrePagamentoSimulacaoEmprestimoDto ppse = new PrePagamentoSimulacaoEmprestimoDto();
        if (LocalDate.now().isBefore(emprestimo.getDataPagamento()) == true) {
            ppse.setIdEmprestimo(emprestimo.getId());
            ppse.setPagamentoAntecipado(true);
            ppse.setDataPagamentoPrevista(emprestimo.getDataPagamento());
            ppse.setValorSolicitado(emprestimo.getValorSolicitado());
            ppse.setDataPagamento(LocalDate.now());

            Period period = emprestimo.getDataPedido().until(ppse.getDataPagamento());
            for (int i = 0; i <= 1000000; i++) {
                if (period.toTotalMonths() == i) {
                    ppse.setJuros((i * 3));
                }
            }

            ppse.setTotalMesesFinanciamento(period.toTotalMonths());
            double juros = (double) ppse.getJuros() / 100;
            BigDecimal jurosBd = BigDecimal.valueOf(juros);
            BigDecimal resultMultiply = ppse.getValorSolicitado().multiply(jurosBd);
            ppse.setTotalPagar(ppse.getValorSolicitado().add(resultMultiply));

        } else {
            ppse.setIdEmprestimo(emprestimo.getId());
            ppse.setPagamentoAntecipado(false);
            ppse.setValorSolicitado(emprestimo.getValorSolicitado());
            ppse.setDataPagamento(emprestimo.getDataPagamento());
            ppse.setJuros(emprestimo.getJuros());
            ppse.setTotalPagar(emprestimo.getTotalPagar());
            ppse.setTotalMesesFinanciamento(emprestimo.getTotalMesesFinanciamento());
        }
        return ppse;
    }

    public PrePagamentoSimulacaoEmprestimoDto tppse(String valorSolicitado, String dataPagamento, String juros, String totalPagar,
                                                    String totalMesesFinanciamento, String idEmprestimo) {
        // Retorna um objeto do tipo PrePagamentoSimulacaoEmprestimoDto.
        PrePagamentoSimulacaoEmprestimoDto ppse = new PrePagamentoSimulacaoEmprestimoDto();
        ppse.setIdEmprestimo(Integer.valueOf(idEmprestimo));
        ppse.setValorSolicitado(dublyUtil.converterDoubleForBigDecimal(valorSolicitado));
        ppse.setDataPagamento(dublyUtil.converterStringForLocalDate(dataPagamento));
        ppse.setJuros(Integer.valueOf(juros));
        ppse.setTotalPagar(dublyUtil.converterDoubleForBigDecimal(totalPagar));
        ppse.setTotalMesesFinanciamento(Long.valueOf(totalMesesFinanciamento));
        return ppse;
    }

    public List<Emprestimo> findByEmprestimoStatusByUser(Conta conta,StatusEmprestimo statusEmprestimo){
        return this.emprestimoRepository.findByEmprestimoStatusByUser(conta,statusEmprestimo);
    }









}
