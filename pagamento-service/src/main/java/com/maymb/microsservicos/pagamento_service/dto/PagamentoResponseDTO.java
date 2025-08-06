package com.maymb.microsservicos.pagamento_service.dto;

import com.maymb.microsservicos.pagamento_service.model.Pagamento;

public class PagamentoResponseDTO {
    private Long id;
    private Double valor;

    private ClienteDTO cliente;



    public PagamentoResponseDTO() {
    }

    public PagamentoResponseDTO(Long id, Double valor, ClienteDTO cliente) {
        this.id = id;
        this.valor = valor;
        this.cliente = cliente;
    }

    public PagamentoResponseDTO(Pagamento pagamento, ClienteDTO cliente) {
        this.id = pagamento.getId();
        this.valor = pagamento.getValor();
        this.cliente = cliente;
    }

    public PagamentoResponseDTO(long l, String mail, double v, String sucesso) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

