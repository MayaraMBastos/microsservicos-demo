package com.maymb.microsservicos.cliente_service.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maymb.microsservicos.cliente_service.controller.ClienteController;
import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.repository.ClienteRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteRepository clienteRepository;

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
        Cliente cliente = new Cliente(1L, "Mayara", "mayara@email.com");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mayara"))
                .andExpect(jsonPath("$.email").value("mayara@email.com"));

        verify(notificacaoProducer, times(1)).enviarMensagem("mayara@email.com");
    }
}
