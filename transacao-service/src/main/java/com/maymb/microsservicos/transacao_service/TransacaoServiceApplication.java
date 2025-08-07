package com.maymb.microsservicos.transacao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TransacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoServiceApplication.class, args);
	}

}
