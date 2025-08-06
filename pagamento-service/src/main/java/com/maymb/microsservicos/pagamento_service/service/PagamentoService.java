package com.maymb.microsservicos.pagamento_service.service;


import com.maymb.microsservicos.pagamento_service.client.ClienteClient;
import com.maymb.microsservicos.pagamento_service.dto.ClienteDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoResponseDTO;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoProducer pagamentoProducer;
    private final ClienteClient clienteClient;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            PagamentoProducer pagamentoProducer,
                            ClienteClient clienteClient) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoProducer = pagamentoProducer;
        this.clienteClient = clienteClient;
    }

    public PagamentoResponseDTO salvarPagamento(PagamentoDTO dto) {
        ClienteDTO cliente = clienteClient.buscarClientePorId(dto.getClienteId());

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(dto.getValor());
        pagamento.setEmail(cliente.getEmail());


        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        // Use o 'pagamentoSalvo' para construir o DTO de resposta.
        PagamentoResponseDTO response = new PagamentoResponseDTO(pagamentoSalvo, cliente);
        pagamentoProducer.enviarTransacao(response);

        return response;
    }
}



