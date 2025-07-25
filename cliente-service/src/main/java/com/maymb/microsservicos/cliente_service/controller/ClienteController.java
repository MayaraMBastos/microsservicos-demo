package com.maymb.microsservicos.cliente_service.controller;

import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por expor endpoints relacionados ao recurso Cliente.
 * Atualmente, permite o cadastro de novos clientes e o envio de notificações
 * via RabbitMQ para o serviço de notificação.
 *
 * Endpoint base: {@code /clientes}
 *
 * @author Mayara Martinello Bastos
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    /**
     * Repositório responsável pela persistência dos dados do cliente no banco de dados.
     */
    @Autowired
    private ClienteRepository repo;

    /**
     * Componente responsável por publicar mensagens na fila de notificação.
     */
    @Autowired
    private NotificacaoProducer notificacaoProducer;

    /**
     * Endpoint para cadastrar um novo cliente.
     * Ao salvar o cliente, uma mensagem com o e-mail é enviada para a fila "fila.notificacao",
     * que será consumida pelo serviço de notificação.
     *
     * @param cliente objeto contendo os dados do cliente a ser cadastrado
     * @return o cliente salvo no banco de dados
     */
    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        Cliente salvo = repo.save(cliente);
        notificacaoProducer.enviarMensagem(salvo.getEmail());
        return salvo;
    }
}
