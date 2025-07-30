package com.maymb.microsservicos.cliente_service.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maymb.microsservicos.cliente_service.controller.ClienteController;
import com.maymb.microsservicos.cliente_service.dto.ClienteDTO;
import com.maymb.microsservicos.cliente_service.dto.ClienteResponseDTO;
import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.repository.ClienteRepository;
import com.maymb.microsservicos.cliente_service.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private NotificacaoProducer notificacaoProducer;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void deveCadastrarClienteComSucesso() throws Exception {
        ClienteDTO dto = new ClienteDTO("Mayara", "mayara@email.com");
        Cliente clienteSalvo = new Cliente(1L, dto.getNome(), dto.getEmail());

        when(clienteService.salvar(any(ClienteDTO.class))).thenReturn(clienteSalvo);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Mayara"))
                .andExpect(jsonPath("$.email").value("mayara@email.com"));

        verify(clienteService, times(1)).salvar(any(ClienteDTO.class));
        verify(notificacaoProducer, times(1)).enviarMensagem("mayara@email.com");
    }


    @Test
    void deveRetornarClientePorIdComSucesso() throws Exception {
        // Arrange
        Long id = 1L;
        ClienteResponseDTO cliente = new ClienteResponseDTO(id, "Mayara", "mayara@email.com");

        when(clienteService.buscarPorId(id)).thenReturn(cliente);

        // Act & Assert
        mockMvc.perform(get("/clientes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Mayara"))
                .andExpect(jsonPath("$.email").value("mayara@email.com"));

        verify(clienteService, times(1)).buscarPorId(id);
    }
}
