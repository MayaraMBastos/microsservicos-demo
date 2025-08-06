package com.maymb.microsservicos.pagamento_service.client;

import com.maymb.microsservicos.pagamento_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteClient {

    @GetMapping("/clientes/{id}")
    ClienteDTO buscarClientePorId(@PathVariable("id") Long id);
}

