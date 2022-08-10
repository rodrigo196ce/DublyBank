package br.com.beta.dublybank.enums;

public enum ValoresRecargaCelular {

    VALOR12(12),
    VALOR15(15),
    VALOR20(20),
    VALOR25(25),
    VALOR35(35),
    VALOR40(40);

    private Integer valor;

    ValoresRecargaCelular(Integer valor){
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}
