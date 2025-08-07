package com.maymb.microsservicos.cliente_service.controller;

import com.maymb.microsservicos.cliente_service.dto.ClienteDTO;
import com.maymb.microsservicos.cliente_service.dto.ClienteResponseDTO;
import com.maymb.microsservicos.cliente_service.messaging.NotificacaoProducer;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * Service responsável por salvar os dados do cliente no banco de dados.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Componente responsável por publicar mensagens na fila de notificação.
     */
    @Autowired
    private NotificacaoProducer notificacaoProducer;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        ClienteResponseDTO dto = clienteService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Endpoint para cadastrar um novo cliente.
     * Ao salvar o cliente, uma mensagem com o e-mail é enviada para a fila "fila.notificacao",
     * que será consumida pelo serviço de notificação.
     *
     * @param dto objeto contendo os dados do cliente a ser cadastrado
     * @return o cliente salvo no banco de dados
     */
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody @Valid ClienteDTO dto) {
        // O serviço retorna a entidade Cliente
        Cliente clienteSalvo = clienteService.salvar(dto);

        // CONVERSÃO NECESSÁRIA: Converta a entidade para DTO
        ClienteResponseDTO responseDto = new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getEmail()
        );

        // Retorna o DTO na resposta
        notificacaoProducer.enviarMensagem(responseDto.getEmail());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
