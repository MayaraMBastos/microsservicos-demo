package com.maymb.microsservicos.pagamento_service.dto;

/**
 * DTO de Requisição
 * Utilizada quando o cliente envia um pagamento
 * **/
public class PagamentoDTO {

    private Double valor;
    private Long clienteId;  // ID do cliente (usado para comunicação com cliente-service)


    public PagamentoDTO(Double valor, Long clienteId) {

        this.valor = valor;
        this.clienteId = clienteId;

    }

    public PagamentoDTO() {
    }

    public PagamentoDTO(String mail, double v) {
    }

    // Getters e Setters


    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
