package com.maymb.microsservicos.pagamento_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class ClienteDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public ClienteDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


