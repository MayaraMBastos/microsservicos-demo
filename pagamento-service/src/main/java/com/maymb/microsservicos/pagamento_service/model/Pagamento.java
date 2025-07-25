package com.maymb.microsservicos.pagamento_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


/**
 * Entidade que representa uma solicitação de pagamento.
 * Inclui informações básicas como valor e e-mail do cliente.
 */
@Entity
public class Pagamento {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private Double valor;

    public Pagamento(Long id, String email, Double valor) {
        this.id = id;
        this.email = email;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
