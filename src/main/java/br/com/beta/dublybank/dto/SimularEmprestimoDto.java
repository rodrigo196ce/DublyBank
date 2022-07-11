package br.com.beta.dublybank.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class SimularEmprestimoDto {
    @NotBlank(message = "O campo é obrigatório.")
    @DecimalMin(value = "1",message = "Valor inválido.")
    private String valor;
    @NotBlank(message = "O campo é obrigatório.")
    private String data;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
