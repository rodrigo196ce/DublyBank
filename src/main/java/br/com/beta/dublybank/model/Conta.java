package br.com.beta.dublybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer conta;
    private Integer agencia;
    private BigDecimal saldo;
    private BigDecimal dinheiroGuardado;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "conta",fetch = FetchType.LAZY)
    private List<Deposito> depositos = new ArrayList<>();
    private Boolean chavePixCpfRegistrada;
    private Boolean chavePixEmailRegistrada;
    private String chavePixAleatoria;
    private BigDecimal valorLimitePix;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "conta", fetch = FetchType.LAZY)
    private List<TransferenciaPix> transferenciasPix = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "conta", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Conta(){}
    public Conta(Integer conta,Integer agencia,User user){
        this.conta = conta;
        this.agencia = agencia;
        this.saldo = new BigDecimal(0);
        this.dinheiroGuardado = new BigDecimal(0);
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getDinheiroGuardado() {
        return dinheiroGuardado;
    }

    public void setDinheiroGuardado(BigDecimal dinheiroGuardado) {
        this.dinheiroGuardado = dinheiroGuardado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
    }

    public Boolean getChavePixCpfRegistrada() {
        return chavePixCpfRegistrada;
    }

    public void setChavePixCpfRegistrada(Boolean chavePixCpfRegistrada) {
        this.chavePixCpfRegistrada = chavePixCpfRegistrada;
    }

    public Boolean getChavePixEmailRegistrada() {
        return chavePixEmailRegistrada;
    }

    public void setChavePixEmailRegistrada(Boolean chavePixEmailRegistrada) {
        this.chavePixEmailRegistrada = chavePixEmailRegistrada;
    }

    public String getChavePixAleatoria() {
        return chavePixAleatoria;
    }

    public void setChavePixAleatoria(String chavePixAleatoria) {
        this.chavePixAleatoria = chavePixAleatoria;
    }

    public BigDecimal getValorLimitePix() {
        return valorLimitePix;
    }

    public void setValorLimitePix(BigDecimal valorLimitePix) {
        this.valorLimitePix = valorLimitePix;
    }

    public List<TransferenciaPix> getTransferenciasPix() {
        return transferenciasPix;
    }

    public void setTransferenciasPix(List<TransferenciaPix> transferenciasPix) {
        this.transferenciasPix = transferenciasPix;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
