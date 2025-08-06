package com.maymb.microsservicos.pagamento_service.controller;

import com.maymb.microsservicos.pagamento_service.dto.PagamentoDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoResponseDTO;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;
import com.maymb.microsservicos.pagamento_service.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para criação de pagamentos.
 * Após o pagamento ser salvo, ele é enviado para a fila de transação.
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoProducer producer;


    public PagamentoController(PagamentoRepository repository, PagamentoService pagamentoService, PagamentoProducer producer) {
        this.repository = repository;
        this.pagamentoService = pagamentoService;
        this.producer = producer;
    }

    /**
     * Endpoint para criar um novo pagamento.
     *
     * @param pagamento objeto com email e valor
     * @return pagamento salvo
     */

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> realizarPagamento(@RequestBody PagamentoDTO pagamento) {
        PagamentoResponseDTO responseDTO = pagamentoService.salvarPagamento(pagamento);
        producer.enviarTransacao(responseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
