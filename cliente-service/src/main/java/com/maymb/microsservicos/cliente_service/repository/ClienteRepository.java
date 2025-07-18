package com.maymb.microsservicos.cliente_service.repository;

import com.maymb.microsservicos.cliente_service.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
