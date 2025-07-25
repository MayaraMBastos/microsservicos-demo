package com.maymb.microsservicos.cliente_service.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do RabbitMQ responsável por declarar a fila utilizada
 * no sistema de mensageria entre os microsserviços.
 *
 * A fila "fila.notificacao" é utilizada para o envio de mensagens de notificação
 * com o e-mail do cliente recém-cadastrado, que será consumida por outro serviço.
 *
 * Esta configuração é gerenciada pelo Spring, através da anotação {@code @Configuration},
 * e registrada automaticamente no contexto da aplicação.
 *
 * @author Mayara Martinello Bastos
 * @version 1.0
 * @since 2025
 */
@Configuration
public class RabbitConfig {

    /**
     * Nome da fila de notificação utilizada para troca de mensagens entre os microsserviços.
     */
    public static final String NOME_FILA = "fila.notificacao";

    /**
     * Cria e registra um bean do tipo {@link Queue} com o nome da fila especificado.
     *
     * @return instância da fila de notificação (não-durável)
     */
    @Bean
    public Queue filaNotificacao() {
        return new Queue(NOME_FILA, false); // false = fila não persiste após reinício do broker
    }
}
