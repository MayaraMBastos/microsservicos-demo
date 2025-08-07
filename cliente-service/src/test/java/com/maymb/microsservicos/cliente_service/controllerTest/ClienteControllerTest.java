package com.maymb.microsservicos.cliente_service.controllerTest;

import com.maymb.microsservicos.cliente_service.controller.ClienteController;
import com.maymb.microsservicos.cliente_service.dto.ClienteDTO;
import com.maymb.microsservicos.cliente_service.dto.ClienteResponseDTO;
import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private NotificacaoProducer notificacaoProducer;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarClienteComSucesso() {
        // 1. Cenário de Teste (Setup)
        ClienteDTO dto = new ClienteDTO("Mayara", "mayara@email.com");

        // CORREÇÃO: Mock do serviço retorna a ENTIDADE Cliente
        Cliente clienteSalvo = new Cliente(1L, "Mayara", "mayara@email.com");
        when(clienteService.salvar(any(ClienteDTO.class))).thenReturn(clienteSalvo);

        // 2. Ação (Execução)
        ResponseEntity<ClienteResponseDTO> response = clienteController.cadastrar(dto);

        // 3. Verificação (Assert)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // O corpo da resposta deve ser o DTO criado pelo controller
        ClienteResponseDTO expectedResponse = new ClienteResponseDTO(1L, "Mayara", "mayara@email.com");
        assertEquals(expectedResponse, response.getBody());

        // Verifique se os métodos dos mocks foram chamados
        verify(clienteService, times(1)).salvar(any(ClienteDTO.class));
        verify(notificacaoProducer, times(1)).enviarMensagem(dto.getEmail());
    }

    @Test
    void deveRetornarClientePorIdComSucesso() {
        // Arrange
        Long id = 1L;
        ClienteResponseDTO mockResponseDTO = new ClienteResponseDTO(id, "Mayara", "mayara@email.com");

        when(clienteService.buscarPorId(id)).thenReturn(mockResponseDTO);

        // Act
        ResponseEntity<ClienteResponseDTO> response = clienteController.buscarPorId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponseDTO, response.getBody());

        verify(clienteService, times(1)).buscarPorId(id);
    }
}