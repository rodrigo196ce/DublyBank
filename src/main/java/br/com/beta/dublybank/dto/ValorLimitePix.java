package br.com.beta.dublybank.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class ValorLimitePix {

    @NotBlank(message = "* O campo é obrigatório.")
    @DecimalMin(value = "1",message = "* Valor inválido.")
    @DecimalMax(value = "1000000",message = "* Valor limite ultrapassado." +
            " Não é permitido transferencias pix acima deste valor.")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
