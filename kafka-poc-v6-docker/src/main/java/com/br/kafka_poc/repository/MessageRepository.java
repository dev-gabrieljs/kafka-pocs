package com.br.kafka_poc.repository;

import com.br.kafka_poc.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

// Interface que estende MongoRepository, fornecendo operações básicas de acesso a dados no MongoDB
public interface MessageRepository extends MongoRepository<Message, String> {
    // Ao estender MongoRepository, essa interface herda métodos como:
    // - save()
    // - findById()
    // - findAll()
    // - deleteById()
    // - entre outros...

    // <Message, String> indica:
    // - Message: o tipo da entidade (documento MongoDB)
    // - String: o tipo do identificador (@Id), que neste caso é uma String

    // Nenhum método adicional é necessário aqui, mas você pode definir consultas personalizadas se precisar
}

