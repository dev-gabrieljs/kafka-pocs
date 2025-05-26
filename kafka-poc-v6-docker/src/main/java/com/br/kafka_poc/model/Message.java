package com.br.kafka_poc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages") // Indica que esta classe representa um documento da coleção "messages" no MongoDB
public class Message {

    @Id // Indica que o campo 'id' é a chave primária (identificador único) do documento
    private String id;

    // Campo que armazena o conteúdo da mensagem
    private String content;

    // Construtor que permite criar uma instância da classe informando apenas o conteúdo
    public Message(String content) {
        this.content = content;
    }

    // Getter para obter o conteúdo da mensagem
    public String getContent() {
        return content;
    }

    // Setter para alterar o conteúdo da mensagem
    public void setContent(String content) {
        this.content = content;
    }
}
