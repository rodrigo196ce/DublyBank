package br.com.beta.dublybank.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DublyUtil {

    public BigDecimal converterDoubleForBigDecimal(String valor){
        Double valorD = Double.valueOf(valor);
        BigDecimal valorB = BigDecimal.valueOf(valorD);
        return valorB;
    }

    public LocalDate converterStringForLocalDate(String data){
        // Converte uma data(String) no formato("yyyy-MM-dd") para um LocalDate.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(data,formatter);
    }

    public Boolean validarDataBefore(String data){
        LocalDate dataF = this.converterStringForLocalDate(data);
        if(dataF.isBefore(LocalDate.now())==true){
            // Retorna true se a dataF é antes da data atual.
            return true;
        }else{
            // Retorna false se a dataF é depois da data atual.
            return false;
        }
    }

}
