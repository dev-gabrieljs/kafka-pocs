package com.br.kafka_poc.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Classe de consumidor Kafka que processa mensagens recebidas de um tópico Kafka.
 * Em caso de erro no processamento, as mensagens são enviadas para uma Dead Letter Queue (DLQ).
 */
@Service
public class KafkaConsumer {

    // Instância do KafkaTemplate para enviar mensagens para o Kafka (como a DLQ).
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Construtor que injeta o KafkaTemplate para enviar mensagens ao Kafka.
     *
     * @param kafkaTemplate O KafkaTemplate que será usado para enviar mensagens para a DLQ.
     */
    public KafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Ouvinte Kafka que processa as mensagens do tópico "meu-topico".
     * O método é chamado sempre que uma nova mensagem é recebida.
     *
     * @param message A mensagem recebida do Kafka.
     */
    @KafkaListener(topics = "meu-topico", groupId = "my-group-id")
    public void listen(String message) {
        try {
            // Exibe a mensagem recebida no console
            System.out.println("Mensagem recebida do Kafka: " + message);

            // Simulação de erro: se a mensagem contém a palavra "erro", uma exceção é lançada
            if (message.contains("erro")) {
                throw new RuntimeException("Erro no processamento da mensagem");
            }

            // Lógica de processamento da mensagem (não implementada no exemplo)

        } catch (Exception e) {
            // Se ocorrer um erro, a mensagem é enviada para a Dead Letter Queue (DLQ)
            sendToDlq(message, e);
        }
    }

    /**
     * Envia uma mensagem para a Dead Letter Queue (DLQ) em caso de erro no processamento.
     *
     * @param message A mensagem que falhou no processamento.
     * @param e A exceção que foi lançada durante o processamento da mensagem.
     */
    private void sendToDlq(String message, Exception e) {
        // Exibe no console que a mensagem está sendo enviada para a DLQ
        System.out.println("Erro no processamento, enviando para DLQ: " + message);

        // Envia a mensagem para o tópico "meu-topico-dlq" (Dead Letter Queue)
        kafkaTemplate.send("meu-topico-dlq", message);
    }
}
