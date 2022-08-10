package br.com.beta.dublybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="recargasCelular")
public class RecargaCelular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Conta conta;
    private String numero;
    private BigDecimal valor;
    private LocalDate data;

    public RecargaCelular(Conta conta,String numero,BigDecimal valor){
        this.conta = conta;
        this.numero = numero;
        this.valor = valor;
        this.data = LocalDate.now();
    }
    public RecargaCelular(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
}
