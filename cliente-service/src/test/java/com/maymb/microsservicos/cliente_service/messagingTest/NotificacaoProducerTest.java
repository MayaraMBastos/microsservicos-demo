package com.maymb.microsservicos.cliente_service.messagingTest;


import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class NotificacaoProducerTest {

    private RabbitTemplate rabbitTemplate;
    private NotificacaoProducer notificacaoProducer;

    @BeforeEach
    void setup() {
        rabbitTemplate = Mockito.mock(RabbitTemplate.class);
        notificacaoProducer = new NotificacaoProducer(rabbitTemplate);
    }

    @Test
    void deveEnviarMensagemParaFila() {
        String email = "teste@email.com";
        notificacaoProducer.enviarMensagem(email);

        verify(rabbitTemplate, times(1))
                .convertAndSend("fila.notificacao", email);
    }
}
