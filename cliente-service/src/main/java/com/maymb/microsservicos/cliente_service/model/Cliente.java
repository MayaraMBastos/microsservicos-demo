package com.maymb.microsservicos.cliente_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entidade que representa um cliente do sistema.
 * Esta classe é persistida no banco de dados através da JPA e utilizada
 * durante o processo de cadastro e notificação.
 *
 * Cada cliente possui um identificador único, um nome e um e-mail, que
 * é utilizado para o envio de mensagens para a fila de notificação.
 *
 * A anotação {@code @Entity} indica que esta classe será mapeada para uma tabela.
 *
 * @author Mayara
 * @version 1.0
 * @since 2025
 */
@Entity
public class Cliente {

    /**
     * Identificador único do cliente, gerado automaticamente.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Nome completo do cliente.
     */
    private String nome;

    /**
     * Endereço de e-mail do cliente, usado para notificações.
     */
    private String email;

    /**
     * Construtor completo com todos os atributos.
     *
     * @param id    identificador do cliente
     * @param nome  nome do cliente
     * @param email e-mail do cliente
     */
    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    /**
     * Obtém o ID do cliente.
     *
     * @return ID do cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     *
     * @param id novo ID a ser atribuído
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome novo nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o e-mail do cliente.
     *
     * @return e-mail do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do cliente.
     *
     * @param email novo e-mail do cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
