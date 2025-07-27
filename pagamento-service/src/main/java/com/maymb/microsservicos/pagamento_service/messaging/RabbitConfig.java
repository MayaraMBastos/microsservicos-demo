package com.maymb.microsservicos.pagamento_service.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory; // CORRETO
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
        return new Queue(FILA_TRANSACAO, false);
    }

    /**
     * Conversor de mensagens para JSON.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Template do RabbitMQ com conversor JSON.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
