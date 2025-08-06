package com.maymb.microsservicos.pagamento_service.messagingTest;

import com.maymb.microsservicos.pagamento_service.dto.ClienteDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoResponseDTO;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
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
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome("Mayara");
        cliente.setEmail("cliente@email.com");

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO();
        pagamento.setId(1L);
        pagamento.setValor(150.0);
        pagamento.setCliente(cliente);

        pagamentoProducer.enviarTransacao(pagamento);

        verify(rabbitTemplate, times(1)).convertAndSend("fila.pagamento", pagamento);
    }
}
