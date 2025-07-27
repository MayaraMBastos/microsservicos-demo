package com.maymb.microsservicos.transacao_service.model;

/**
 * Representa os dados recebidos de um pagamento da fila.
 */
public class Pagamento {
    private Long id;
    private String email;
    private Double valor;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
