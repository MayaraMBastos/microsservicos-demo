package com.maymb.microsservicos.pagamento_service.model;

import com.maymb.microsservicos.pagamento_service.dto.ClienteDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


/**
 * Entidade que representa uma solicitação de pagamento.
 * Inclui informações básicas como valor e e-mail do cliente.
 */
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;


    private String nome;


    private String email;

    public Pagamento() {}


    public Pagamento(Long id, Double valor, String nome, String email) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
