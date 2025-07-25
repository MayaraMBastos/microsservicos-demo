package com.maymb.microsservicos.pagamento_service.repository;

import com.maymb.microsservicos.pagamento_service.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável pela persistência dos pagamentos no banco de dados.
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
