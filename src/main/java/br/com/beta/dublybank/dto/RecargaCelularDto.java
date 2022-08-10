package br.com.beta.dublybank.dto;

import br.com.beta.dublybank.enums.ValoresRecargaCelular;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class RecargaCelularDto {

    @NotBlank(message = "O campo é obrigatório.")
    private String numero;
    @Enumerated
    private ValoresRecargaCelular valoresRecargaCelular;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ValoresRecargaCelular getValoresRecargaCelular() {
        return valoresRecargaCelular;
    }

    public void setValoresRecargaCelular(ValoresRecargaCelular valoresRecargaCelular) {
        this.valoresRecargaCelular = valoresRecargaCelular;
    }
}
