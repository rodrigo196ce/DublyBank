package br.com.beta.dublybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valorSolicitado;
    private LocalDate dataPagamento;
    private Integer juros;
    private BigDecimal totalPagar;
    private Boolean concluido;
    @ManyToOne
    private Conta conta;
    private Long totalMesesFinanciamento;

    public Emprestimo(){}
    public Emprestimo(BigDecimal valorSolicitado,LocalDate dataPagamento,Conta conta){
        this.valorSolicitado = valorSolicitado;
        this.dataPagamento = dataPagamento;
        this.concluido = false;
        this.conta = conta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Long getTotalMesesFinanciamento() {
        return totalMesesFinanciamento;
    }
    public void setTotalMesesFinanciamento(Long totalMesesFinanciamento) {
        this.totalMesesFinanciamento = totalMesesFinanciamento;
    }
}
