package com.maymb.microsservicos.cliente_service.controller;

import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.repository.ClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private NotificacaoProducer notificacaoProducer;

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        Cliente salvo = repo.save(cliente);
        notificacaoProducer.enviarMensagem(salvo.getEmail());
        return salvo;
    }
}
