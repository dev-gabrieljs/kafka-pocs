package com.br.kafka_poc.controller;

import com.br.kafka_poc.producer.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class KafkaStartupRunner implements CommandLineRunner {

    private final KafkaProducer kafkaProducer;

    public KafkaStartupRunner(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            String message = "Mensagem automática #" + i;
            kafkaProducer.sendMessage(message);
            System.out.println("Mensagem enviada ao Kafka: " + message);

            // Aguarda 5 segundos antes de enviar a próxima
            Thread.sleep(5000);
        }
    }
}
