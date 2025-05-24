# Kafka POÇ (Proof of Concept) - Spring Boot Kafka Integration
Este repositório contém um exemplo de integração entre uma aplicação Spring Boot e o Apache Kafka, utilizando Docker para orquestrar a execução de um broker Kafka e a aplicação Spring Boot.

## Estrutura do Projeto
O projeto possui três componentes principais:

1. **KafkaProducer**: Serviço responsável por enviar mensagens para um tópico no Apache Kafka.
2. **KafkaConsumer**: Serviço responsável por consumir mensagens de um tópico no Apache Kafka.
3. **Configuração do Docker Compose**: Arquitetura para rodar o Kafka e a aplicação Spring Boot em containers Docker.

## Componentes

### 1. KafkaConsumer
O serviço `KafkaConsumer` é responsável por consumir mensagens publicadas no tópico `test-topic` do Apache Kafka. A classe utiliza a anotação `@KafkaListener` para escutar as mensagens.

#### Código:

```java
@Service
@EnableKafka
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
```

### 2. KafkaProducer
O serviço `KafkaProducer` envia mensagens para o tópico `test-topic` no Apache Kafka. Ele usa a classe `KafkaTemplate` para interagir com o Kafka.

#### Código:

```java
@Service
@EnableKafka
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String kafkaTopic = "test-topic";

    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaTopic, message);
    }
}


```

### 3. Configuração do Kafka no `application.properties`
A configuração do Kafka é realizada no arquivo `application.properties`, onde são definidos os parâmetros de conexão do broker Kafka, serializadores e deserializadores.

#### Exemplo:

```properties
spring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
spring.kafka.consumer.group-id=test-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.properties.session.timeout.ms=30000
spring.kafka.consumer.properties.request.timeout.ms=40000
spring.kafka.consumer.properties.max.poll.interval.ms=300000
spring.kafka.consumer.properties.retry.backoff.ms=1000
```

### 4. Dockerfile
O `Dockerfile` descreve o processo de construção e execução do aplicativo Java utilizando Maven e OpenJDK. Ele é dividido em duas etapas:

- **Stage 1**: Build do JAR com Maven.
- **Stage 2**: Execução do JAR com OpenJDK.

#### Dockerfile:

```dockerfile
# Stage 1: build do jar com Maven
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: runtime com OpenJDK
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/kafka-poc-v1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 5. Docker Compose
O **Docker Compose** define os containers para a aplicação Spring Boot e o broker Kafka. Ele configura a rede entre os containers e a comunicação entre a aplicação e o Kafka.

#### Exemplo de configuração do Docker Compose:

```yaml
services:
  broker:
    image: apache/kafka:latest
    container_name: broker
    ports:
      - 9092:9092
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://broker:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@broker:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0
      KAFKA_NUM_PARTITIONS: 3

  app:
    build: .
    container_name: spring-app
    ports:
      - "8080:8080"
    environment:
      SPRING_KAFKA_BOOTSTRAP_SERVERS: broker:9092
    depends_on:
      - broker
```

### Como Rodar a Aplicação

#### Pré-requisitos
- Docker instalado no seu sistema.
- Docker Compose instalado.
- Maven instalado, caso queira construir a aplicação manualmente.

#### Passos:
1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
cd <nome-do-repositorio>
```

### Como Rodar a Aplicação

#### Passos para Construção e Execução dos Containers:

1. **Construa os containers usando o Docker Compose:**

```bash
docker-compose up --build
```

Isso irá iniciar dois containers:

- **broker**: Kafka broker.
- **spring-app**: A aplicação Spring Boot.

#### Verifique os logs do consumidor:

Após a inicialização, você pode verificar os logs da aplicação Spring Boot para ver as mensagens consumidas do Kafka.

```bash
docker logs spring-app
```

#### Envie mensagens via Kafka:

Para enviar uma mensagem, você pode utilizar o `KafkaProducer` da aplicação ou utilizar ferramentas externas para produzir mensagens para o tópico `test-topic`.

---

### Testando o Kafka

- **Consuma mensagens** com o `KafkaConsumer`, que vai imprimir as mensagens recebidas no log.
- **Produza mensagens** com o `KafkaProducer`, que envia uma mensagem para o Kafka, a qual será consumida pelo `KafkaConsumer`.
