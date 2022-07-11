package br.com.beta.dublybank.dto;

import javax.validation.constraints.NotBlank;

public class ValidarIdentidadeDepositoDto {

    @NotBlank(message = "O campo é obrigatório.")
    private String senha;
    private String valor;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
