package com.br.kafka_poc.consumer;

import com.br.kafka_poc.model.Message;
import com.br.kafka_poc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String messageContent) {
        System.out.println("Mensagem recebida: " + messageContent);

        // Salvar a mensagem no MongoDB
        Message message = new Message(messageContent);
        messageRepository.save(message);
        System.out.println("Mensagem salva no MongoDB: " + messageContent);
    }
}
