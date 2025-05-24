# Guia de Instalação e Funcionamento do Apache Kafka

O Apache Kafka é uma plataforma de streaming distribuída de código aberto usada para construir pipelines de dados em tempo real e aplicativos de streaming. O Kafka permite a publicação, assinatura, armazenamento e processamento de fluxos de dados em tempo real.

Este README vai guiar você na instalação do Kafka e explicar como ele funciona de forma simples.

## O que é o Kafka?

Kafka é uma plataforma de mensageria distribuída e orientada a logs, que permite o envio de mensagens entre sistemas. Ele é usado em arquiteturas de microservices e outros sistemas em tempo real, proporcionando alta performance, escalabilidade e durabilidade.

### Principais Componentes do Kafka:
- **Producer**: Responsável por enviar dados (mensagens) para tópicos do Kafka.
- **Consumer**: Responsável por ler e processar dados de tópicos Kafka.
- **Broker**: Um servidor Kafka que armazena os dados e os gerencia.
- **Topic**: Um canal de mensagens para onde os produtores enviam dados e os consumidores leem.
- **Partition**: Divisão de um tópico, que ajuda a distribuir dados entre múltiplos brokers para escalabilidade.

## Como o Kafka Funciona?

1. **Produção de Dados**: 
   - O produtor (Producer) envia mensagens para um ou mais tópicos no Kafka.
   - As mensagens são armazenadas no Kafka de forma distribuída, em partições (Partitions).

2. **Armazenamento de Dados**:
   - O Kafka armazena mensagens de forma eficiente e durável, garantindo que não haja perda de dados.
   - Mensagens são armazenadas com um **offset**, que é um identificador único de cada mensagem.

3. **Consumo de Dados**:
   - O consumidor (Consumer) lê dados do Kafka e pode processá-los ou analisá-los.
   - O Kafka garante que as mensagens sejam entregues exatamente uma vez (sem duplicação) ou pelo menos uma vez, dependendo da configuração.

## Como Instalar o Kafka?

### Pré-Requisitos:
Antes de instalar o Kafka, você precisa ter o seguinte instalado:

- [Java 8 ou superior](https://adoptopenjdk.net/) (O Kafka é escrito em Java)
- [Zookeeper](https://zookeeper.apache.org/) (Kafka utiliza o Zookeeper para coordenar a distribuição de dados e a gestão de brokers)

### Passo 1: Baixar e Instalar o Apache Kafka

1. Baixe a versão mais recente do Kafka diretamente do site oficial:
   
   - [Apache Kafka - Downloads](https://kafka.apache.org/downloads)

2. Extraia o arquivo baixado para um diretório de sua preferência. No Linux, você pode usar os seguintes comandos:

   ```bash
   tar -xzf kafka_2.13-2.8.0.tgz
   cd kafka_2.13-2.8.0
   ```
   # Passos para Executar o Apache Kafka

## Passo 2: Iniciar o Zookeeper

O Kafka depende do Zookeeper para gerenciar o cluster e coordenar a distribuição de dados.

1. Inicie o Zookeeper executando o seguinte comando:

   ```bash
   bin/zookeeper-server-start.sh config/zookeeper.properties
# Passos para Executar o Apache Kafka

## Passo 3: Iniciar o Kafka Broker

O Kafka Broker é o servidor que gerencia a produção e consumo de mensagens. Agora que o Zookeeper está em execução, você pode iniciar o Kafka Broker.

1. Execute o seguinte comando para iniciar o Kafka:

   ```bash
   bin/kafka-server-start.sh config/server.properties

  # Passos para Executar o Apache Kafka

## Passo 4: Criar um Tópico Kafka

No Kafka, os dados são enviados e consumidos por meio de tópicos. Você pode criar um tópico para começar a enviar e receber mensagens.

1. Execute o comando abaixo para criar um tópico chamado `meu-topico` com 1 partição e 1 fator de replicação:

   ```bash
   bin/kafka-topics.sh --create --topic meu-topico --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Passos para Executar o Apache Kafka

## Passo 5: Criar um Tópico Kafka

No Kafka, os dados são enviados e consumidos por meio de tópicos. Você pode criar um tópico para começar a enviar e receber mensagens.

1. Execute o comando abaixo para criar um tópico chamado `meu-topico` com 1 partição e 1 fator de replicação:

   ```bash
   bin/kafka-topics.sh --create --topic meu-topico --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1


    --topic: Nome do tópico a ser criado.

    --bootstrap-server: Endereço do broker Kafka.

    --partitions: Número de partições para o tópico.

    --replication-factor: Número de réplicas do tópico para garantir a disponibilidade dos dados.
   ```

   # Guia de Uso do Apache Kafka

## Passo 4: Enviar Mensagens para o Tópico (Producer)

Agora que o tópico foi criado, você pode enviar mensagens para ele.

1. Execute o seguinte comando para iniciar um producer que enviará mensagens para o tópico `meu-topico`:

   ```bash
   bin/kafka-console-producer.sh --broker-list localhost:9092 --topic meu-topico
## Passo 5: Consumir Mensagens do Tópico (Consumer)

Agora, vamos consumir as mensagens que foram enviadas para o tópico.

1. Execute o seguinte comando para iniciar um consumer que irá ler as mensagens do tópico `meu-topico`:

   ```bash
   bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic meu-topico --from-beginning
## Exemplo de Fluxo de Mensagens

1. **Producer** envia uma mensagem para o Kafka:

Mensagem 1
Mensagem 2
Mensagem 3


2. **Consumer** recebe e imprime as mensagens do Kafka:
Mensagem 1 recebida do Kafka
Mensagem 2 recebida do Kafka
Mensagem 3 recebida do Kafka



