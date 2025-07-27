package com.maymb.microsservicos.pagamento_service.messaging;

import com.maymb.microsservicos.pagamento_service.model.Pagamento;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Produtor responsável por enviar os dados do pagamento para a fila de transações.
 */
@Component
public class TransacaoProducer {

    private static final String FILA_TRANSACAO = "fila.transacao";
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TransacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * Envia os dados do pagamento para a fila "fila.transacao".
     *
     * @param pagamento pagamento a ser processado
     */
    public void enviarParaFila(Pagamento pagamento){
        rabbitTemplate.convertAndSend(FILA_TRANSACAO, pagamento);
        System.out.println("Pagamento enviado para análise: " + pagamento.getEmail());
    }
}
