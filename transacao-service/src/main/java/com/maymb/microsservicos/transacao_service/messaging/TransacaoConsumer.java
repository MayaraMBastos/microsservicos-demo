package com.maymb.microsservicos.transacao_service.messaging;

import com.maymb.microsservicos.transacao_service.model.Pagamento;
import com.maymb.microsservicos.transacao_service.repository.TransacaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoConsumer {

    private final TransacaoRepository transacaoRepository;

    public TransacaoConsumer(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @RabbitListener(queues = RabbitConfig.FILA_TRANSACAO)
    public void processarTransacao(Pagamento pagamento) {
        System.out.printf(" Transação recebida: ID=%d | Email=%s | Valor=R$ %.2f%n",
                pagamento.getId(), pagamento.getEmail(), pagamento.getValor());

        // Lógica de persistência adicionada
        transacaoRepository.save(pagamento);

        System.out.println("Transação aprovada e salva no banco de dados!");
    }
}