package com.maymb.microsservicos.pagamento_service.controllerTest;

import com.maymb.microsservicos.pagamento_service.controller.PagamentoController;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoDTO;
import com.maymb.microsservicos.pagamento_service.dto.PagamentoResponseDTO;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;
import com.maymb.microsservicos.pagamento_service.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {

    // A anotação @InjectMocks vai tentar criar uma instância de PagamentoController
    // e injetar os mocks (@Mock) nele.
    @InjectMocks
    private PagamentoController pagamentoController;

    // Os mocks para as dependências do Controller
    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoProducer producer;

    // Adicione o mock para PagamentoRepository, pois ele também é uma dependência
    // no construtor do seu controller
    @Mock
    private PagamentoRepository repository;

    @BeforeEach
    void setup() {
        // Inicializa os mocks criados acima
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarStatus201_QuandoPagamentoForCriadoComSucesso() {
        // 1. Cenário de Teste (Setup)
        PagamentoDTO pagamentoDTO = new PagamentoDTO("test@email.com", 100.0);
        PagamentoResponseDTO mockResponseDTO = new PagamentoResponseDTO(1L, "test@email.com", 100.0, "SUCESSO");

        // Defina o comportamento do mock do PagamentoService
        when(pagamentoService.salvarPagamento(pagamentoDTO)).thenReturn(mockResponseDTO);

        // 2. Ação (Execução)
        ResponseEntity<PagamentoResponseDTO> response = pagamentoController.realizarPagamento(pagamentoDTO);

        // 3. Verificação (Assert)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponseDTO, response.getBody());

        // Verifique se os métodos dos mocks foram chamados
        verify(pagamentoService, times(1)).salvarPagamento(pagamentoDTO);
        verify(producer, times(1)).enviarTransacao(mockResponseDTO);

        // Verifique se PagamentoService é injetado corretamente
       // assertNotNull(pagamentoController.getPagamentoService());
    }
}