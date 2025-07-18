package com.maymb.microsservicos.cliente_service.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String NOME_FILA = "fila.notificacao";

    @Bean
    public Queue filaNotificacao() {
        return new Queue(NOME_FILA, false); // nao-durável (ou true se quiser persistência)
    }
}

