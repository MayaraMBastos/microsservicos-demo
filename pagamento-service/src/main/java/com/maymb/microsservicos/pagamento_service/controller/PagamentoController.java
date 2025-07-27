package com.maymb.microsservicos.pagamento_service.controller;

import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PagamentoProducer producer;

    /**
     * Endpoint para criar um novo pagamento.
     *
     * @param pagamento objeto com email e valor
     * @return pagamento salvo
     */
    @PostMapping
    public Pagamento criar(@RequestBody Pagamento pagamento) {
        Pagamento salvo = repository.save(pagamento);
        producer.enviarParaFila(salvo);
        return salvo;
    }
}
