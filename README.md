# Guia de Instalação e Funcionamento do Apache Kafka

O Apache Kafka é uma plataforma de streaming distribuída de código aberto, projetada para permitir a criação de pipelines de dados em tempo real e aplicações de processamento de fluxos de dados. Kafka oferece uma infraestrutura robusta e escalável para publicar, assinar, armazenar e processar fluxos de dados, tornando-o uma ferramenta fundamental em arquiteturas modernas de microserviços e processamento de dados em tempo real.


## O que é o Kafka?

O Apache Kafka é uma plataforma de mensageria distribuída baseada em logs, projetada para enviar grandes volumes de dados em tempo real entre sistemas. Ele é altamente escalável, durável e fornece garantias de entrega de mensagens. Kafka é utilizado em sistemas que exigem processamentos em tempo real e alta disponibilidade, como sistemas de monitoramento, análise de logs e integração de microserviços.

### Principais Características do Apache Kafka:
- **Desempenho Elevado**: Kafka consegue processar grandes volumes de dados de maneira eficiente, podendo suportar milhões de mensagens por segundo.
- **Escalabilidade Horizontal**: Ele permite a adição de novos nós (brokers) ao cluster sem comprometer a performance, podendo escalar facilmente.
- **Durabilidade**: As mensagens são persistidas em disco e podem ser replicadas entre múltiplos brokers para garantir a alta disponibilidade e recuperação de falhas.
- **Processamento em Tempo Real**: Kafka permite a criação de sistemas que processam dados em tempo real, sem a necessidade de intermediários ou buffers.


## Principais componentes do Kafka:

- **Producer**: Aplicações que enviam dados (mensagens) para o Kafka. Os produtores escrevem mensagens para tópicos.

- **Consumer**: Aplicações que leem dados dos tópicos Kafka e os processam. Os consumidores se inscrevem em tópicos para receber mensagens.
  
- **Broker**: Servidor que faz o gerenciamento de dados, incluindo o armazenamento e o envio das mensagens para os consumidores. Um cluster Kafka pode ter múltiplos brokers.
  
- **Topic**: Canal de mensagens dentro do Kafka. Os dados são organizados em tópicos e tanto produtores quanto consumidores interagem com eles.
  
- **Partition**: Cada tópico é dividido em partições. Cada partição é uma sequência ordenada de mensagens, o que permite paralelizar a leitura e a escrita de dados de forma eficiente.
  
- **Offset**: Um identificador único de cada mensagem dentro de uma partição. Ele permite que os consumidores leiam as mensagens de forma precisa, mantendo o controle sobre o que foi processado.

## Como o Kafka Funciona?

## Como o Kafka Funciona?

O Apache Kafka é baseado em um modelo de publicação e assinatura de mensagens, mas com algumas diferenças importantes que o tornam eficiente para dados de streaming em larga escala. A seguir, explicamos o ciclo de funcionamento básico.

### 1. Produção de Dados:
- O **Producer** envia dados para tópicos Kafka. Um produtor pode enviar dados para um único ou múltiplos tópicos, e pode ser configurado para fazer isso de maneira assíncrona.
- As mensagens são armazenadas nas partições de um tópico.

### 2. Armazenamento de Dados:
- Kafka armazena as mensagens de forma persistente e distribuída.
- Cada mensagem é identificada por um **offset** único, que é usado para garantir que as mensagens sejam entregues na ordem certa e para facilitar a leitura contínua.

### 3. Consumo de Dados:
- O **Consumer** lê mensagens dos tópicos Kafka. Os consumidores podem ler as mensagens desde o início ou a partir de um ponto específico.
- Kafka oferece garantias de entrega como "pelo menos uma vez", "exatamente uma vez" ou "no máximo uma vez", dependendo da configuração.

### 4. Escalabilidade e Replicação:
- Para garantir alta disponibilidade, Kafka replica as partições entre múltiplos brokers.
- Em caso de falha de um broker, outros brokers podem assumir a responsabilidade pelas partições para garantir que os dados não sejam perdidos.


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

# Guia de Instalação e Funcionamento do Apache Kafka via Docker

# Iniciar Kafka em um contêiner Docker simples (Modo de um único nó)

## Passo 1: Iniciar o Corretor Kafka
Execute o comando abaixo para iniciar um contêiner Kafka com a imagem mais recente:

```bash
docker run -d --name broker apache/kafka:latest
```
## Passo 2: Acessar o Shell do Contêiner
Para acessar o shell dentro do contêiner e interagir com o Kafka, use o comando:

```bash
docker exec --workdir /opt/kafka/bin/ -it broker sh
```

## Passo 3: Criar um Tópico
Agora, crie um tópico chamado `test-topic`:

```bash
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test-topic
```

## Passo 4: Produzir Mensagens no Tópico
Use o produtor de console para enviar mensagens para o tópico criado:

```bash
./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-topic
```

## Passo 5: Consumir Mensagens do Tópico
Para consumir as mensagens produzidas, execute:

```bash
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```
## Passo 6: Parar e Remover o Contêiner
Quando terminar, pare e remova o contêiner com o comando:

```bash
docker rm -f broker
```

# Substituindo Configurações Padrão do Broker Kafka

## Passo 1: Substituindo Variáveis de Ambiente
Você pode substituir configurações padrão do Kafka no Docker definindo variáveis de ambiente. Por exemplo, para definir o número de partições padrão como 3, use:

```bash
docker run -d  \
  --name broker \
  -e KAFKA_NODE_ID=1 \
  -e KAFKA_PROCESS_ROLES=broker,controller \
  -e KAFKA_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
  -e KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0 \
  -e KAFKA_NUM_PARTITIONS=3 \
  apache/kafka:latest

```

## Passo 2: Usando Docker Compose
Em vez de passar muitas variáveis pela linha de comando, você pode usar Docker Compose para facilitar a configuração. Crie um arquivo chamado `docker-compose.yml` com o seguinte conteúdo:

```yaml
services:
  broker:
    image: apache/kafka:latest
    container_name: broker
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_LISTENERS: PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@localhost:9093
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0
      KAFKA_NUM_PARTITIONS: 3
```
Para iniciar o contêiner em segundo plano, execute:

```bash
docker-compose up -d
```
Para parar e remover os contêineres, execute:

```bash
docker-compose down
```
# Clientes Externos
Se quiser executar comandos do Kafka fora do Docker, você deve mapear a porta para a máquina host. Para fazer isso, use a opção `-p`:

## Usando Docker
```bash
docker run -d -p 9092:9092 --name broker apache/kafka:latest
```
Ou no Docker Compose, adicione o mapeamento de portas:

```yaml
services:
  broker:
    image: apache/kafka:latest
    container_name: broker
    ports:
      - 9092:9092
```

# Kafka com Múltiplos Nós (Modo KRaft)

Aqui está um exemplo de configuração com três brokers e três controladores em modo KRaft (sem Zookeeper), adequado para uma configuração de produção em pequena escala. Use o Docker Compose para facilitar a configuração de múltiplos nós.

## Passo 1: Criar `docker-compose.yml`
Crie um arquivo `docker-compose.yml` com o seguinte conteúdo:

```yaml
version: '2'
services:
  controller-1:
    image: apache/kafka:latest
    container_name: controller-1
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: controller
      KAFKA_LISTENERS: CONTROLLER://:9093
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093

  controller-2:
    image: apache/kafka:latest
    container_name: controller-2
    environment:
      KAFKA_NODE_ID: 2
      KAFKA_PROCESS_ROLES: controller
      KAFKA_LISTENERS: CONTROLLER://:9093
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093

  controller-3:
    image: apache/kafka:latest
    container_name: controller-3
    environment:
      KAFKA_NODE_ID: 3
      KAFKA_PROCESS_ROLES: controller
      KAFKA_LISTENERS: CONTROLLER://:9093
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093

  broker-1:
    image: apache/kafka:latest
    container_name: broker-1
    ports:
      - 29092:9092
    environment:
      KAFKA_NODE_ID: 4
      KAFKA_PROCESS_ROLES: broker
      KAFKA_LISTENERS: 'PLAINTEXT://:19092,PLAINTEXT_HOST://:9092'
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://broker-1:19092,PLAINTEXT_HOST://localhost:29092'
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

  broker-2:
    image: apache/kafka:latest
    container_name: broker-2
    ports:
      - 39092:9092
    environment:
      KAFKA_NODE_ID: 5
      KAFKA_PROCESS_ROLES: broker
      KAFKA_LISTENERS: 'PLAINTEXT://:19092,PLAINTEXT_HOST://:9092'
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://broker-2:19092,PLAINTEXT_HOST://localhost:39092'
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

  broker-3:
    image: apache/kafka:latest
    container_name: broker-3
    ports:
      - 49092:9092
    environment:
      KAFKA_NODE_ID: 6
      KAFKA_PROCESS_ROLES: broker
      KAFKA_LISTENERS: 'PLAINTEXT://:19092,PLAINTEXT_HOST://:9092'
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://broker-3:19092,PLAINTEXT_HOST://localhost:49092'
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@controller-1:9093,2@controller-2:9093,3@controller-3:9093
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0

```
## Passo 2: Iniciar os Contêineres
Execute os seguintes comandos para iniciar os contêineres:

```bash
docker-compose up -d
```
## Passo 3: Produzir e Consumir Mensagens
Agora, dentro de qualquer contêiner broker, você pode criar tópicos, produzir e consumir mensagens.

### Para criar um tópico, use:

```bash
./kafka-topics.sh --bootstrap-server broker-1:19092,broker-2:19092,broker-3:19092 --create --topic test-topic
```
Para produzir mensagens:
```bash
./kafka-console-producer.sh --bootstrap-server broker-1:19092,broker-2:19092,broker-3:19092 --topic test-topic
```
Para consumir mensagens:
```bash
./kafka-console-consumer.sh --bootstrap-server broker-1:19092,broker-2:19092,broker-3:19092 --topic test-topic --from-beginning
```
## Passo 4: Parar e Remover os Contêineres
Para remover todos os contêineres, execute:

```bash
docker-compose down




