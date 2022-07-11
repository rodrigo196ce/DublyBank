package br.com.beta.dublybank.dto;

import br.com.beta.dublybank.model.User;

import javax.validation.constraints.NotBlank;

public class CadastroUserDto {
    @NotBlank(message = "O campo é obrigatório.")
    private String username;
    @NotBlank(message = "O campo é obrigatório.")
    private String password;
    @NotBlank(message = "O campo é obrigatório.")
    private String nome;
    @NotBlank(message = "O campo é obrigatório.")
    private String cpf;
    @NotBlank(message = "O campo é obrigatório.")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public User toUser(){
        return new User(this.username,this.password,this.nome,this.cpf,this.email);
    }
}
