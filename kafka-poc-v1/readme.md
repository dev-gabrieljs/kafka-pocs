# Kafka Poc - Exemplo de Integração com Kafka

Este projeto é um exemplo de como integrar uma aplicação Spring Boot com Kafka para enviar e consumir mensagens. O código envia mensagens para um tópico Kafka de forma assíncrona com intervalos de 2 segundos, e o consumidor escuta as mensagens recebidas desse tópico.

## Funcionalidade

- **Producer**: Envia mensagens para um tópico Kafka (`meu-topico`) a cada 2 segundos, com um atraso progressivo.
- **Consumer**: Escuta as mensagens recebidas no tópico Kafka e exibe elas no console.

## Estrutura do Projeto

O projeto é composto pelas seguintes classes:

- **`KafkaApplicationRunner`**: Classe que executa o envio das mensagens para o Kafka com intervalos de 2 segundos usando um `ScheduledExecutorService`.
- **`KafkaProducer`**: Classe que envia mensagens para o Kafka usando `KafkaTemplate`.
- **`KafkaConsumer`**: Classe que escuta as mensagens do tópico Kafka usando `@KafkaListener`.

## Pré-Requisitos

Antes de rodar o projeto, certifique-se de que os seguintes pré-requisitos estão instalados em seu ambiente:

- [Java 11 ou superior](https://adoptopenjdk.net/)
- [Apache Kafka](https://kafka.apache.org/) (Local ou remoto)
- [Maven](https://maven.apache.org/)

## Configuração

### Kafka

- O projeto está configurado para se conectar a um broker Kafka rodando localmente na porta `9092`.
- O tópico Kafka utilizado é `meu-topico`.

### Arquivo de Configuração (`application.properties`)

Certifique-se de configurar o `application.properties` da seguinte forma:

```properties
# Nome do tópico Kafka utilizado pelo Producer e Consumer
kafka.topic.name=meu-topico

# Configuração do Kafka Broker (onde o Kafka está rodando)
spring.kafka.bootstrap-servers=localhost:9092
# Endereço do servidor Kafka. Nesse caso, está rodando localmente na porta 9092.

# Configuração do Consumer Kafka
spring.kafka.consumer.group-id=my-group-id
# ID do grupo do consumidor. Consumidores com o mesmo group-id compartilham a carga de mensagens.

spring.kafka.consumer.auto-offset-reset=earliest
# Configuração do comportamento do Consumer quanto ao offset:
# "earliest" - começa a ler do início do tópico (todas as mensagens).
# "latest" - começa a ler a partir da última mensagem.
# "none" - Lança erro caso o offset não esteja disponível.

# Configuração do Producer Kafka
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
# Serializador da chave da mensagem do Producer.

spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
# Serializador do valor da mensagem do Producer.

# Configuração do Consumer Kafka
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
# Deserializador da chave da mensagem do Consumer.

spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
# Deserializador do valor da mensagem do Consumer.
```

## Como Executar o Projeto

### Passo 1: Iniciar o Kafka

Certifique-se de que o Kafka está rodando. Você pode iniciar o Kafka localmente ou configurar um broker remoto.

1. Baixe e instale o Kafka, caso ainda não tenha feito isso: [Kafka Downloads](https://kafka.apache.org/downloads).
2. Inicie o Kafka com o comando:

```bash
# Iniciar o Zookeeper (se necessário)
bin/zookeeper-server-start.sh config/zookeeper.properties

# Iniciar o Kafka Broker
bin/kafka-server-start.sh config/server.properties
```
## Como Funciona

- **Producer**: O `KafkaProducer` envia mensagens para o tópico configurado (`meu-topico`) a cada 2 segundos. O intervalo aumenta progressivamente para cada nova mensagem.
- **Consumer**: O `KafkaConsumer` escuta o tópico Kafka (`meu-topico`) e imprime as mensagens no console à medida que são recebidas.

### Exemplo de Log

O log do console será semelhante a este:

Mensagem número 1 enviada com sucesso!
Mensagem número 2 enviada com sucesso!
Mensagem número 3 enviada com sucesso!
...
Mensagem recebida do Kafka: Mensagem número 1
Mensagem recebida do Kafka: Mensagem número 2
Mensagem recebida do Kafka: Mensagem número 3
