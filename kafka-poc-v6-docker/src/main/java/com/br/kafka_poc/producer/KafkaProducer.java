package com.br.kafka_poc.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
