package com.maymb.microsservicos.pagamento_service.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuração da fila de transações.
 */
@Configuration
public class RabbitConfig {

    public static final String FILA_TRANSACAO = "fila.transacao";

    /**
     * Cria a fila que envia os pagamentos para o serviço de transações.
     */
    @Bean
    public Queue filaTransacao(){
        return new Queue (FILA_TRANSACAO, false);

    }
}
