package com.maymb.microsservicos.cliente_service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por enviar mensagens para a fila de notificação.
 * Utiliza o {@link RabbitTemplate} para publicar mensagens no RabbitMQ.
 *
 * Essa classe é utilizada pelo {@code ClienteController} após o cadastro
 * de um cliente, com o objetivo de enviar seu e-mail para a fila "fila.notificacao".
 * A mensagem será consumida por outro microsserviço que simula o envio de e-mails.
 *
 * @author Mayara Martinello Bastos
 * @version 1.0
 * @since 2025
 */
@Component
public class NotificacaoProducer {

    /**
     * Nome da fila onde a mensagem será publicada.
     */
    private static final String NOME_FILA = "fila.notificacao";

    private final RabbitTemplate rabbitTemplate;

    /**
     * Construtor que injeta o {@link RabbitTemplate}, utilizado para envio das mensagens.
     *
     * @param rabbitTemplate componente do Spring AMQP usado para comunicação com o RabbitMQ
     */
    @Autowired
    public NotificacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Envia uma mensagem para a fila de notificação contendo o e-mail do cliente.
     *
     * @param email endereço de e-mail do cliente a ser notificado
     */
    public void enviarMensagem(String email) {
        rabbitTemplate.convertAndSend(NOME_FILA, email);
        System.out.println("Mensagem enviada para a fila: " + email);
    }
}


