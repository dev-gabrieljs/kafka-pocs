package com.br.kafka_poc.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")  // Lê a propriedade do arquivo application.properties
    private String topic;

    /**
     * Método responsável por enviar a mensagem para o tópico Kafka configurado.
     * A mensagem é enviada de forma assíncrona usando o KafkaTemplate.
     *
     * @param message A mensagem que será enviada para o Kafka.
     */
    public void sendMessage(String message) {
        System.out.println("Enviando mensagem: " + message);
        kafkaTemplate.send(topic, message);  // Envia a mensagem para o tópico Kafka
    }
}
