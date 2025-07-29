package com.maymb.microsservicos.pagamento_service.messagingTest;


import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

class PagamentoProducerTest {

    private RabbitTemplate rabbitTemplate;
    private PagamentoProducer pagamentoProducer;

    @BeforeEach
    void setup() {
        rabbitTemplate = Mockito.mock(RabbitTemplate.class);
        pagamentoProducer = new PagamentoProducer(rabbitTemplate);
    }

    @Test
    void deveEnviarPagamentoParaFila() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setEmail("cliente@email.com");
        pagamento.setValor(150.0);

        pagamentoProducer.enviarTransacao(pagamento);

        verify(rabbitTemplate, times(1))
                .convertAndSend("fila.pagamento", pagamento);
    }
}

