package com.maymb.microsservicos.transacao_service.messaging;

import com.maymb.microsservicos.transacao_service.model.Pagamento;
import com.maymb.microsservicos.transacao_service.repository.TransacaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoConsumer {

    public TransacaoConsumer(TransacaoRepository transacaoRepository) {
    }

    @RabbitListener(queues = RabbitConfig.FILA_TRANSACAO)
    public void processarTransacao(Pagamento pagamento) {
        System.out.printf(" Transação recebida: ID=%d | Email=%s | Valor=R$ %.2f%n",
                pagamento.getId(), pagamento.getEmail(), pagamento.getValor());

        // Aqui poderia estar a lógica para validar ou integrar com operadora, etc.
        System.out.println("Transação aprovada!");
    }
}
