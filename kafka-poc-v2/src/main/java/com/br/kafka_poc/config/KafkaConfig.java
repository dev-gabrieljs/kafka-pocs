package com.br.kafka_poc.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuração do Kafka para consumo de mensagens.
 * Esta classe define a configuração necessária para o consumidor Kafka.
 * O consumidor se conecta ao broker Kafka, consome mensagens e as processa.
 */
@Configuration
@EnableKafka // Habilita o uso do Kafka dentro do contexto do Spring.
public class KafkaConfig {

    /**
     * Criação do ConsumerFactory, que é responsável por fornecer a fábrica de consumidores Kafka.
     * O ConsumerFactory usa a configuração de consumo definida no método 'consumerConfigs'.
     *
     * @return O ConsumerFactory configurado para consumir mensagens com chave e valor do tipo String.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * Configura os parâmetros necessários para o consumidor Kafka, como:
     * - Endereço do broker Kafka (localhost:9092).
     * - ID do grupo de consumidores.
     * - Deserializadores para chave e valor (no caso, String).
     * - Configuração do comportamento de offset (no caso, 'earliest' para começar do início).
     *
     * @return O mapa contendo a configuração do consumidor.
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = new HashMap<>();

        // Configura o endereço do broker Kafka.
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Configura o ID do grupo de consumidores.
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");

        // Configura o deserializador para a chave da mensagem (usando String).
        consumerConfigs.put("key.deserializer", StringDeserializer.class);

        // Configura o deserializador para o valor da mensagem (usando String).
        consumerConfigs.put("value.deserializer", StringDeserializer.class);

        // Configura o comportamento de offset. "earliest" significa começar do início da fila.
        consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return consumerConfigs;
    }

}
