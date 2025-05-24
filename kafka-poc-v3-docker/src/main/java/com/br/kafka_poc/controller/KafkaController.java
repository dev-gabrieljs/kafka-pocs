package com.br.kafka_poc.controller;

import com.br.kafka_poc.producer.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST responsável por expor um endpoint HTTP
 * que envia mensagens para o Kafka através do produtor.
 */
@RestController
public class KafkaController {

    // Injeta o serviço KafkaProducer via construtor
    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Endpoint HTTP POST que recebe uma mensagem no corpo da requisição
     * e a envia para o Kafka.
     *
     * @param message - Texto da mensagem recebido no corpo da requisição.
     */
    @PostMapping("/send")
    public void send(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
    }
}
