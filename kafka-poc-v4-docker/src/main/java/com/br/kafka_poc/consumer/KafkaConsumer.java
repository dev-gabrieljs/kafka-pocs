package com.br.kafka_poc.consumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por consumir mensagens do Apache Kafka.
 * A anotação @EnableKafka habilita a detecção de listeners anotados com @KafkaListener.
 */
@Service
@EnableKafka
public class KafkaConsumer {

    /**
     * Método que escuta mensagens do tópico "test-topic".
     *
     * @KafkaListener - Informa que esse método será automaticamente invocado
     * quando novas mensagens forem publicadas no tópico especificado.
     *
     * groupId - Identifica o grupo de consumidores que compartilham o consumo do tópico.
     *
     * @param message - Conteúdo da mensagem recebida do Kafka.
     */
    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
