package com.br.kafka_poc.consumer;

import com.br.kafka_poc.model.Message;
import com.br.kafka_poc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service // Define a classe como um serviço Spring, permitindo sua injeção e gerenciamento como bean
public class KafkaConsumer {

    @Autowired // Injeta automaticamente uma instância de MessageRepository no campo abaixo
    private MessageRepository messageRepository;

    // Método que será executado automaticamente quando uma mensagem for recebida no tópico "test-topic"
    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String messageContent) {
        // Exibe no console o conteúdo da mensagem recebida do Kafka
        System.out.println("Mensagem recebida: " + messageContent);

        // Cria um novo objeto Message com o conteúdo recebido
        Message message = new Message(messageContent);

        // Salva o objeto Message no MongoDB através do repositório
        messageRepository.save(message);

        // Exibe no console a confirmação de que a mensagem foi salva
        System.out.println("Mensagem salva no MongoDB: " + messageContent);
    }
}

