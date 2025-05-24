package com.br.kafka_poc.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Classe responsável pelo envio de mensagens para o Kafka.
 * Inclui uma lógica de tentativas de reenvio em caso de falha, utilizando backoff exponencial.
 */
@Service
public class KafkaProducer {

    // KafkaTemplate é utilizado para enviar mensagens ao Kafka.
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // O nome do tópico para onde as mensagens serão enviadas é recuperado da configuração do application.properties.
    @Value("${kafka.topic.name}")
    private String topic;

    // Número máximo de tentativas para enviar a mensagem, configurado no application.properties.
    @Value("${kafka.retry.attempts}")
    private int retryAttempts;

    /**
     * Método responsável por enviar uma mensagem para o Kafka com tentativas em caso de falha.
     *
     * @param message A mensagem que será enviada para o Kafka.
     */
    public void sendMessage(String message) {
        // Contador de tentativas
        int attempts = 0;

        // Flag que indica se a mensagem foi enviada com sucesso
        boolean sent = false;

        // Tenta enviar a mensagem até atingir o número máximo de tentativas
        while (!sent && attempts < retryAttempts) {
            try {
                // Exibe no console que a mensagem está sendo enviada
                System.out.println("Enviando mensagem: " + message);

                // Envia a mensagem para o tópico Kafka
                kafkaTemplate.send(topic, message);

                // Se a mensagem for enviada com sucesso, a flag "sent" é atualizada
                sent = true;

                // Exibe no console que a mensagem foi enviada com sucesso
                System.out.println("Mensagem enviada com sucesso: " + message);
            } catch (Exception e) {
                // Se ocorrer um erro, incrementa o contador de tentativas
                attempts++;

                // Exibe no console que houve um erro e qual a tentativa atual
                System.out.println("Erro ao enviar mensagem. Tentativa " + attempts + " de " + retryAttempts);

                // Se o número de tentativas exceder o máximo configurado, a falha é reportada
                if (attempts >= retryAttempts) {
                    System.out.println("Falha ao enviar mensagem após " + retryAttempts + " tentativas.");
                    break;
                }

                // Calcula o tempo de espera (backoff exponencial) antes de tentar novamente
                try {
                    long backoff = (long) Math.pow(2, attempts); // Exponencial: 1, 2, 4, 8, 16 segundos, etc.
                    TimeUnit.SECONDS.sleep(backoff); // Aguarda o tempo calculado antes de tentar novamente
                } catch (InterruptedException ie) {
                    // Caso o thread seja interrompido, trata a interrupção
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
