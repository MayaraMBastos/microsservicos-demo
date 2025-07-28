package com.maymb.microsservicos.transacao_service.repository;

import com.maymb.microsservicos.transacao_service.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Pagamento, Long> {
}
