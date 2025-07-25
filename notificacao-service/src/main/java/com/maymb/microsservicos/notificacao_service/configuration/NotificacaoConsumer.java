package com.maymb.microsservicos.notificacao_service.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por consumir mensagens da fila de notificação.
 *
 * Esta classe escuta a fila {@code "fila.notificacao"} utilizando a anotação {@link RabbitListener},
 * e simula o envio de uma notificação ao receber uma mensagem contendo o e-mail do cliente.
 *
 * O processamento é feito de forma assíncrona, como parte da arquitetura orientada a eventos
 * entre microsserviços.
 *
 * @author Mayara
 * @version 1.0
 * @since 2025
 */
@Component
public class NotificacaoConsumer {

    /**
     * Método executado automaticamente sempre que uma nova mensagem é recebida da fila.
     * Neste exemplo, a notificação é apenas simulada com uma mensagem no console.
     *
     * @param email e-mail do cliente recebido via mensagem da fila
     */
    @RabbitListener(queues = "fila.notificacao")
    public void receberMensagem(String email) {
        System.out.println("Enviando notificação para: " + email);
    }
}
