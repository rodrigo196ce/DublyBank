package br.com.beta.dublybank.model;

import br.com.beta.dublybank.enums.StatusEmprestimo;

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
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
    @ManyToOne
    private Conta conta;
    private Long totalMesesFinanciamento;
    private LocalDate dataPedido;

    public Emprestimo(){}
    public Emprestimo(BigDecimal valorSolicitado,LocalDate dataPagamento){
        this.valorSolicitado = valorSolicitado;
        this.dataPagamento = dataPagamento;
        this.status = StatusEmprestimo.SIMULACAO;
        this.dataPedido = LocalDate.now();
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

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
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

    public LocalDate getDataPedido() {
        return dataPedido;
    }
}
