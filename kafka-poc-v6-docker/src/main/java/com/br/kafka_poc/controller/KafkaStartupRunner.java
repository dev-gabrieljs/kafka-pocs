package com.br.kafka_poc.controller;

import com.br.kafka_poc.producer.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component // Marca essa classe como um componente Spring, permitindo que ela seja detectada e gerenciada como bean
public class KafkaStartupRunner implements CommandLineRunner {

    // Declara uma dependência do produtor Kafka
    private final KafkaProducer kafkaProducer;

    // Construtor que recebe uma instância de KafkaProducer via injeção de dependência
    public KafkaStartupRunner(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        // Executa este bloco de código automaticamente na inicialização da aplicação

        for (int i = 1; i <= 10; i++) {
            // Cria uma mensagem com base no número da iteração
            String message = "Mensagem automática #" + i;

            // Envia a mensagem para o Kafka usando o produtor
            kafkaProducer.sendMessage(message);

            // Exibe no console que a mensagem foi enviada
            System.out.println("Mensagem enviada ao Kafka: " + message);

            // Aguarda 5 segundos antes de enviar a próxima mensagem
            Thread.sleep(5000);
        }
    }
}
