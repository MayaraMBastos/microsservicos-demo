package com.maymb.microsservicos.notificacao_service.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    @RabbitListener(queues = "fila.notificacao")
    public void receberMensagem(String email) {
        System.out.println("Enviando notificação para: " + email);
    }
}