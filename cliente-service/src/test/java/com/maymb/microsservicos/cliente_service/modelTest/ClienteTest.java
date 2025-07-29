package com.maymb.microsservicos.cliente_service.modelTest;


import com.maymb.microsservicos.cliente_service.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteCorretamente() {
        Long id = 1L;
        String nome = "Mayara";
        String email = "mayara@email.com";

        Cliente cliente = new Cliente(id, nome, email);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
    }

    @Test
    void deveSetarEAcessarCampos() {
        Cliente cliente = new Cliente();
        cliente.setId(2L);
        cliente.setNome("Paulo");
        cliente.setEmail("paulo@email.com");

        assertEquals(2L, cliente.getId());
        assertEquals("Paulo", cliente.getNome());
        assertEquals("paulo@email.com", cliente.getEmail());
    }
}

