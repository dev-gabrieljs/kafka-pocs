package com.br.kafka_poc.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por enviar mensagens para o Apache Kafka.
 */
@Service
@EnableKafka
public class KafkaProducer {

    /**
     * KafkaTemplate é a classe principal usada para interagir com o Kafka em aplicações Spring.
     * Aqui, é injetada automaticamente pelo Spring com base na configuração definida em application.properties.
     * O template está parametrizado com chave e valor do tipo String.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Nome do tópico para o qual as mensagens serão enviadas.
     */
    private String kafkaTopic = "test-topic";

    /**
     * Envia uma mensagem para o tópico Kafka definido.
     *
     * @param message - Conteúdo da mensagem a ser enviada.
     */
    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaTopic, message);
    }
}
