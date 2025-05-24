package com.br.kafka_poc.repository;

import com.br.kafka_poc.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    // Métodos personalizados, se necessário
}
