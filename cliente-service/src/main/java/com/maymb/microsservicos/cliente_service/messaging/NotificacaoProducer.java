package com.maymb.microsservicos.cliente_service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String NOME_FILA = "fila.notificacao";

    @Autowired
    public NotificacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String email) {
        rabbitTemplate.convertAndSend(NOME_FILA, email);
        System.out.println("Mensagem enviada para a fila: " + email);
    }
}

