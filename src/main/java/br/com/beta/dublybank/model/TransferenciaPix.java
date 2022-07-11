package br.com.beta.dublybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="transferenciasPix")
public class TransferenciaPix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal valor;
    private LocalDate data;
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;
    private String nomeDestinatario;
    private Integer agencia;
    private Integer contac; // conta

    public TransferenciaPix(){}
    public TransferenciaPix(BigDecimal valor,LocalDate data,Conta conta, String nomeDestinatario
            , Integer agencia, Integer contac){
        this.valor = valor;
        this.data = data;
        this.conta = conta;
        this.nomeDestinatario = nomeDestinatario;
        this.agencia = agencia;
        this.contac = contac;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getContac() {
        return contac;
    }

    public void setContac(Integer contac) {
        this.contac = contac;
    }
}
