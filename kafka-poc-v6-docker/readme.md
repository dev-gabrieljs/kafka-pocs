# Kafka POC com Spring Boot, MongoDB e Docker

Este projeto é uma prova de conceito que demonstra a integração entre Apache Kafka, Spring Boot e MongoDB, utilizando Docker para facilitar o ambiente e a orquestração dos serviços.

---

## Funcionamento do Projeto

### 1. Produtor de Mensagens Kafka

- Ao iniciar a aplicação Spring Boot, o componente `KafkaStartupRunner` é executado automaticamente.
- Ele envia 10 mensagens automáticas para o tópico Kafka chamado `test-topic`, com intervalos de 5 segundos entre elas.
- As mensagens são enviadas pelo `KafkaProducer`, que usa o `KafkaTemplate` do Spring Kafka para publicar no Kafka.

### 2. Consumidor Kafka

- A aplicação possui um consumidor configurado na classe `KafkaConsumer`.
- Esse consumidor escuta o tópico `test-topic` com o grupo `test-group`.
- Ao receber uma mensagem, ele imprime no console e em seguida cria um objeto `Message` com o conteúdo da mensagem.
- O objeto `Message` é então salvo no banco MongoDB através do `MessageRepository`.

### 3. Persistência no MongoDB

- As mensagens recebidas do Kafka são persistidas na coleção `messages` do banco `kafka_db`.
- A persistência é feita usando Spring Data MongoDB.
- O MongoDB roda em um container Docker, garantindo um ambiente isolado e facilmente replicável.

### 4. Interface Web para Visualização (Mongo Express)

- Para facilitar o monitoramento, o projeto inclui o container do **Mongo Express**, que fornece uma interface web simples para visualizar os dados no MongoDB.
- A interface pode ser acessada localmente na porta `8081`.

### 5. Orquestração com Docker Compose

- O projeto inclui um arquivo `docker-compose.yml` que configura e sobe os containers necessários para rodar o Kafka, MongoDB, Mongo Express e a aplicação Spring Boot.
- A aplicação Spring Boot espera o MongoDB estar disponível antes de iniciar, garantindo que a conexão seja bem-sucedida.

---

## Resumo do Fluxo

```plaintext
KafkaStartupRunner (envia mensagens)
        ↓
Kafka (test-topic)
        ↓
KafkaConsumer (escuta e consome mensagens)
        ↓
MongoDB (salva mensagens)
        ↓
Mongo Express (interface para visualizar mensagens)
```

### Serviços:

- **broker**  
  - Container do Kafka usando a imagem oficial `apache/kafka:latest`.  
  - Expõe a porta `9092` para comunicação.  
  - Configura variáveis essenciais do Kafka para funcionar como broker e controller, definindo listeners, IDs e fatores de replicação.  

- **mongo**  
  - Container do MongoDB com imagem oficial `mongo:latest`.  
  - Expõe a porta `27017` para conexões ao banco.  
  - Define usuário e senha de root para autenticação.  
  - Possui volume persistente para armazenar dados localmente (`./data/db`).  

- **mongo-express**  
  - Interface web para administrar o MongoDB.  
  - Usa a imagem oficial `mongo-express`.  
  - Expõe a porta `8081` para acesso via navegador.  
  - Conecta ao MongoDB com usuário e senha configurados.  

- **app**  
  - Container da aplicação Spring Boot.  
  - Builda a aplicação usando o Dockerfile local.  
  - Expõe a porta `8080` para acesso à API.  
  - Usa o script `wait-for-it.sh` para garantir que o MongoDB esteja pronto antes de iniciar.  
  - Recebe as variáveis de ambiente para conectar no Kafka (`broker:9092`) e MongoDB.  
  - Depende dos serviços `broker` e `mongo` para garantir ordem na inicialização.  

---

## Comandos Docker para rodar o projeto

### Build e subir todos os containers

```bash
docker-compose up --build
```
Este comando irá:

- Construir a imagem da aplicação Spring Boot usando o Dockerfile.
- Subir os containers do Kafka, MongoDB, Mongo Express e aplicação.
- Orquestrar a comunicação e dependências entre eles.

---

### Rodar containers em segundo plano (detached)

```bash
docker-compose up -d
```

### Parar e remover containers

```bash
docker-compose down
``` 
- Para e remove todos os containers criados pelo docker-compose.

