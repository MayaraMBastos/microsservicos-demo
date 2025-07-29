package com.maymb.microsservicos.transacao_service.messagingTest;


import com.maymb.microsservicos.transacao_service.messaging.TransacaoConsumer;
import com.maymb.microsservicos.transacao_service.model.Pagamento;

import com.maymb.microsservicos.transacao_service.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class TransacaoConsumerTest {

    private TransacaoRepository transacaoRepository;
    private TransacaoConsumer transacaoConsumer;

    @BeforeEach
    void setup() {
        transacaoRepository = Mockito.mock(TransacaoRepository.class);
        transacaoConsumer = new TransacaoConsumer(transacaoRepository);
    }

    @Test
    void deveSalvarTransacaoRecebida() {
        Pagamento transacao = new Pagamento();
        transacao.setEmail("cliente@email.com");
        transacao.setValor(300.0);

        transacaoConsumer.processarTransacao(transacao);

        verify(transacaoRepository, times(1)).save(transacao);
    }
}
