package com.br.kafka_poc.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Define essa classe como um serviço do Spring, permitindo sua injeção como dependência
@EnableKafka // Habilita o suporte ao Kafka no contexto da aplicação Spring
public class KafkaProducer {

    @Autowired // Injeta automaticamente uma instância de KafkaTemplate
    private KafkaTemplate<String, String> kafkaTemplate;

    // Define o nome do tópico Kafka onde as mensagens serão enviadas
    private String kafkaTopic = "test-topic";

    // Método responsável por enviar mensagens para o Kafka
    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaTopic, message); // Envia a mensagem para o tópico especificado
    }
}
