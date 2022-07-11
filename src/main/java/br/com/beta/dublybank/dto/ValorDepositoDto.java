package br.com.beta.dublybank.dto;

import javax.validation.constraints.NotBlank;

public class ValorDepositoDto {

    @NotBlank(message = "O campo é obrigatório.")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
