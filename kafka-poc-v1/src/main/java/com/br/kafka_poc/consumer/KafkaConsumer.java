package com.br.kafka_poc.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    /**
     * Método que escuta as mensagens de um tópico do Kafka.
     * A anotação @KafkaListener permite que o método 'listen' seja chamado automaticamente
     * sempre que uma mensagem for recebida no tópico especificado.
     *
     * @param message A mensagem recebida do Kafka, que será processada.
     */
    @KafkaListener(topics = "meu-topico", groupId = "my-group-id")
    public void listen(String message) {
        // Exibe a mensagem recebida no console
        System.out.println("Mensagem recebida do Kafka: " + message);
    }
}
