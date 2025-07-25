package com.maymb.microsservicos.cliente_service.repository;

import com.maymb.microsservicos.cliente_service.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável por gerenciar as operações de persistência
 * da entidade {@link Cliente} no banco de dados.
 *
 * Esta interface estende {@link JpaRepository}, o que fornece automaticamente
 * métodos como {@code save}, {@code findAll}, {@code findById}, {@code delete}, etc.
 *
 * Não é necessário implementar nada manualmente, pois o Spring Data JPA
 * gera a implementação em tempo de execução.
 *
 * Pode ser estendida futuramente com métodos personalizados de consulta,
 * como {@code findByEmail}, {@code findByNomeContaining}, etc.
 *
 * @author Mayara Martinello Bastos
 * @version 1.0
 * @since 2025
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
