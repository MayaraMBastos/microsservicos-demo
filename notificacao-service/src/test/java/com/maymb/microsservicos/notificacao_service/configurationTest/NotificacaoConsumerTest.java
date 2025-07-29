package com.maymb.microsservicos.notificacao_service.configurationTest;


import com.maymb.microsservicos.notificacao_service.configuration.NotificacaoConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class NotificacaoConsumerTest {

    private NotificacaoConsumer notificacaoConsumer;

    @BeforeEach
    void setup() {
        notificacaoConsumer = spy(new NotificacaoConsumer());
    }

    @Test
    void deveReceberMensagemDaFila() {
        // Dado
        String email = "teste@email.com";

        // Quando
        notificacaoConsumer.receberMensagem(email);

        // Então
        verify(notificacaoConsumer).receberMensagem(email);
    }
}
