package br.com.beta.dublybank.dto;

import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

public class EditarDto {

    @NotBlank(message = "* O campo é obrigatório.")
    private String nome;
    @NotBlank(message = "* O campo é obrigatório.")
    private String cpf;
    @NotBlank(message = "* O campo é obrigatório.")
    private String email;
    @NotBlank(message = "* O campo é obrigatório.")
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void toUser(User user){
        this.nome = user.getNome();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.telefone = user.getTelefone();
    }

}
