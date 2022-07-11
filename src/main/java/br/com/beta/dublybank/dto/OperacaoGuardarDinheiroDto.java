package br.com.beta.dublybank.dto;

import javax.validation.constraints.*;

public class OperacaoGuardarDinheiroDto {

    @NotBlank(message = "O campo é obrigatório.")
    @DecimalMin(value = "1",message = "O valor deve ser acima de zero.")
    private String valor;

    public OperacaoGuardarDinheiroDto(){}

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
