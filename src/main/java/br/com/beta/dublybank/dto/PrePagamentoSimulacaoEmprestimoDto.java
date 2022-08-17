package br.com.beta.dublybank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrePagamentoSimulacaoEmprestimoDto {
    private BigDecimal valorSolicitado;
    private LocalDate dataPagamento;
    private Integer juros;
    private BigDecimal totalPagar;
    private Long totalMesesFinanciamento;
    private Boolean pagamentoAntecipado;
    private LocalDate dataPagamentoPrevista;
    private Integer idEmprestimo;

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(BigDecimal valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getJuros() {
        return juros;
    }

    public void setJuros(Integer juros) {
        this.juros = juros;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Long getTotalMesesFinanciamento() {
        return totalMesesFinanciamento;
    }

    public void setTotalMesesFinanciamento(Long totalMesesFinanciamento) {
        this.totalMesesFinanciamento = totalMesesFinanciamento;
    }

    public Boolean getPagamentoAntecipado() {
        return pagamentoAntecipado;
    }

    public void setPagamentoAntecipado(Boolean pagamentoAntecipado) {
        this.pagamentoAntecipado = pagamentoAntecipado;
    }

    public LocalDate getDataPagamentoPrevista() {
        return dataPagamentoPrevista;
    }

    public void setDataPagamentoPrevista(LocalDate dataPagamentoPrevista) {
        this.dataPagamentoPrevista = dataPagamentoPrevista;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
}
