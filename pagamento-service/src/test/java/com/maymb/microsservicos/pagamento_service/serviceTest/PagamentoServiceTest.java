package com.maymb.microsservicos.pagamento_service.serviceTest;


import com.maymb.microsservicos.pagamento_service.client.ClienteClient;

import com.maymb.microsservicos.pagamento_service.dto.ClienteDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoResponseDTO;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;
import com.maymb.microsservicos.pagamento_service.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ClienteClient clienteFeignClient;

    @Mock
    private PagamentoProducer pagamentoProducer;

    @InjectMocks
    private PagamentoService pagamentoService;

    private PagamentoDTO pagamentoDTO;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setClienteId(1L);
        pagamentoDTO.setValor(200.0);

        clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Mayara");
        clienteDTO.setEmail("cliente@email.com");
    }

    @Test
    void deveSalvarPagamentoComSucesso() {
        Pagamento pagamentoSalvo = new Pagamento();
        pagamentoSalvo.setId(10L);
        pagamentoSalvo.setValor(pagamentoDTO.getValor());

        when(clienteFeignClient.buscarClientePorId(1L)).thenReturn(clienteDTO);
        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamentoSalvo);

        PagamentoResponseDTO response = pagamentoService.salvarPagamento(pagamentoDTO);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("cliente@email.com", response.getCliente().getEmail());
        verify(clienteFeignClient, times(1)).buscarClientePorId(1L);
        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
    }
}

