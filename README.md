
# ⚙️ Microsserviços - Cadastro e Notificação

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green.svg)](https://spring.io/projects/spring-boot)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange.svg)](https://www.rabbitmq.com/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)

## 📌 Sobre o Projeto

Este projeto demonstra uma arquitetura simples de **microsserviços com Java e Spring Boot**, utilizando **RabbitMQ** como ferramenta de mensageria. Ele simula um fluxo comum em sistemas modernos: ao cadastrar um cliente, uma mensagem é enviada para outro serviço responsável por notificação (simulada com `System.out`).

## 🧩 Serviços Envolvidos

| Serviço               | Função                                                                 |
|----------------------|------------------------------------------------------------------------|
| `cliente-service`     | API REST para cadastro de clientes, que envia mensagens via RabbitMQ  |
| `notificacao-service` | Escuta mensagens da fila e simula envio de notificação (console log)  |

## 🔧 Funcionalidades

- ✅ Cadastro de cliente com Spring Boot e H2/PostgreSQL
- ✅ Envio de mensagens via RabbitMQ após criação do cliente
- ✅ Consumo de mensagens com `@RabbitListener`
- ✅ Estrutura modular para escalar novos serviços
- ✅ Possível deploy com Docker e Docker Compose

## 📦 Arquitetura Geral

```mermaid
graph TD
A[POST /clientes] --> B[cliente-service]
B --> C[RabbitMQ - fila.notificacao]
C --> D[notificacao-service]
D --> E[Console: "Email enviado para cliente@email.com"]
```

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA**
- **RabbitMQ (mensageria)**
- **H2 Database** (ou PostgreSQL)
- **Docker / Docker Compose (opcional)**

## 📁 Estrutura de Pastas

```
microsservicos-demo/
├── cliente-service/
│   ├── controller/
│   ├── model/
│   ├── repository/
│   ├── messaging/
│   └── ClienteServiceApplication.java
│
├── notificacao-service/
│   ├── consumer/
│   ├── config/
│   └── NotificacaoServiceApplication.java
```

## 🚀 Como Executar Localmente

### Pré-requisitos

- Java 21+
- Maven
- RabbitMQ rodando localmente
  ```bash
  docker run -d --name rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
  ```

### Executando os microsserviços

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/microsservicos-demo.git
cd microsservicos-demo
```

2. Em dois terminais separados, execute:
```bash
cd cliente-service
./mvnw spring-boot:run
```
```bash
cd notificacao-service
./mvnw spring-boot:run
```

3. Faça uma requisição POST:
```http
POST http://localhost:8080/clientes
Content-Type: application/json

{
  "nome": "Mayara",
  "email": "mayara@email.com"
}
```

4. Veja a notificação aparecendo no console do `notificacao-service`:
```
Enviando notificação para: mayara@email.com
```

## 🔐 Endpoints Disponíveis

### `cliente-service`

#### POST `/clientes`
Cadastra um cliente e dispara notificação.

**Exemplo de corpo da requisição:**
```json
{
  "nome": "Mayara",
  "email": "mayara@email.com"
}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Mayara",
  "email": "mayara@email.com"
}
```

## 👩‍💻 Autora

**Mayara Martinello Bastos**  
🌐 [linkedin.com/in/mayara-martinello-bastos](https://www.linkedin.com/in/mayara-martinello-bastos)  
📫 maybastos2021@gmail.com  
