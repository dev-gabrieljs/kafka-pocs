# Kafka POC - Spring Boot Integration

Este projeto é uma prova de conceito (POC) para integrar o Apache Kafka com uma aplicação Spring Boot. O sistema envia mensagens para um tópico Kafka em intervalos programados e processa as mensagens utilizando um consumidor que lida com falhas de processamento através de Dead Letter Queue (DLQ).

## Funcionalidades

- **Producer**: Envia mensagens para o Kafka em intervalos de tempo programados.
- **Consumer**: Processa mensagens de um tópico Kafka, e em caso de erro, redireciona as mensagens para uma Dead Letter Queue (DLQ).
- **Retry Mechanism**: O produtor possui um mecanismo de reenvio com backoff exponencial em caso de falha.
- **Spring Boot**: A aplicação utiliza o framework Spring Boot para gerenciar a execução e a injeção de dependências.

## Estrutura do Projeto

O projeto é dividido em três componentes principais:

1. **KafkaProducer**: Envia mensagens para o Kafka com tentativas de reenvio em caso de falhas.
2. **KafkaConsumer**: Escuta mensagens do Kafka e envia para a DLQ caso haja falha no processamento.
3. **KafkaApplicationRunner**: Inicializa o envio programado de mensagens ao Kafka quando a aplicação Spring Boot é iniciada.

## Pré-requisitos

- JDK 11 ou superior
- Apache Kafka (instalado e rodando localmente ou remotamente)
- Maven ou Gradle para gerenciamento de dependências

## Instalação

### Passo 1: Clone o repositório

```bash
git clone https://github.com/seu-usuario/kafka-poc.git
cd kafka-poc
```
## Passo 2: Instale as dependências

Se estiver usando Maven:

```bash
mvn clean install
```

## Passo 3: Inicie o Kafka

Certifique-se de que o Apache Kafka está rodando localmente ou remotamente. O projeto está configurado para conectar-se a um broker Kafka rodando na URL `localhost:9092`.

## Passo 4: Configure as variáveis no `application.properties`

Edite o arquivo `src/main/resources/application.properties` com as configurações de Kafka:

```properties
# Nome do tópico Kafka onde as mensagens serão enviadas
kafka.topic.name=meu-topico

# Endereço do servidor Kafka
spring.kafka.bootstrap-servers=localhost:9092

# Identificação do grupo de consumidores
spring.kafka.consumer.group-id=my-group-id

# Número de threads de consumidores
spring.kafka.listener.concurrency=3

# Configuração de Retry para o Kafka Producer
kafka.retry.attempts=5

# Configuração de Batching e Compressão
spring.kafka.producer.batch-size=16384
spring.kafka.producer.compression-type=gzip

```
## Passo 5: Execute a aplicação

Com tudo configurado, você pode iniciar a aplicação Spring Boot com o comando:

### Se estiver usando Maven:

```bash
mvn spring-boot:run
```
## Funcionalidade de Envio de Mensagens

O componente `KafkaApplicationRunner` irá enviar automaticamente 40 mensagens para o Kafka, com intervalos de 2 segundos entre cada mensagem. Cada mensagem será enviada para o tópico configurado em `application.properties`.

### Exemplo de saída:

```bash
Mensagem número 1 enviada com sucesso!
Mensagem número 2 enviada com sucesso!
...
Mensagem número 40 enviada com sucesso!
```
## Funcionalidade de Consumo de Mensagens

O consumidor (`KafkaConsumer`) irá escutar o tópico `meu-topico`. Se uma mensagem contiver a palavra "erro", ela será processada como um erro e enviada para a **Dead Letter Queue** (`meu-topico-dlq`).

### Exemplo de saída para uma mensagem com erro:

```bash
Mensagem recebida do Kafka: Mensagem com erro
Erro no processamento da mensagem, enviando para DLQ: Mensagem com erro


