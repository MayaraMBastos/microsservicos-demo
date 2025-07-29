package com.maymb.microsservicos.cliente_service.service;

import com.maymb.microsservicos.cliente_service.dto.ClienteDTO;
import com.maymb.microsservicos.cliente_service.model.Cliente;
import com.maymb.microsservicos.cliente_service.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    /**
     * Repositório responsável pela persistência dos dados do cliente no banco de dados.
     */
    private final ClienteRepository repository;

    /**
     * Construtor completo com todos os atributos.
     *
     * @param repository  repositório do cliente
     */
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
    /**
     * Metodo para criar novo cliente e salvar dados  no banco
     *
     * @param dto  objeto de classe de transicao de dados do cliente
     */
    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        return repository.save(cliente);
    }
}
