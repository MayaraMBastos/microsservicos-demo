package com.maymb.microsservicos.pagamento_service.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maymb.microsservicos.pagamento_service.controller.PagamentoController;
import com.maymb.microsservicos.pagamento_service.messaging.PagamentoProducer;
import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import com.maymb.microsservicos.pagamento_service.repository.PagamentoRepository;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoController.class)
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoRepository pagamentoRepository;

    @MockBean
    private PagamentoProducer pagamentoProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRealizarPagamentoComSucesso() throws Exception {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setEmail("pagador@email.com");
        pagamento.setValor(150.00);

        when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("pagador@email.com"))
                .andExpect(jsonPath("$.valor").value(150.00));

        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
        verify(pagamentoProducer, times(1)).enviarTransacao(any(Pagamento.class));
    }
}

