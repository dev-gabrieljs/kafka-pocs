package com.br.kafka_poc.component;

import com.br.kafka_poc.producer.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe responsável por enviar mensagens para o Kafka de maneira assíncrona e com intervalos de 2 segundos.
 * Implementa o CommandLineRunner, permitindo a execução do código quando a aplicação for inicializada.
 *
 * A classe utiliza o ScheduledExecutorService para agendar o envio de mensagens para o Kafka a cada 2 segundos.
 * Cada mensagem é enviada com um atraso progressivo, ou seja, a primeira mensagem será enviada após 2 segundos,
 * a segunda após 4 segundos, e assim por diante até a 40ª mensagem.
 */
@Component
public class KafkaApplicationRunner implements CommandLineRunner {

    private final KafkaProducer kafkaProducer;

    /**
     * Construtor para injetar a dependência de KafkaProducer.
     *
     * @param kafkaProducer Instância do produtor Kafka para enviar as mensagens.
     */
    public KafkaApplicationRunner(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Método responsável por agendar e enviar mensagens para o Kafka.
     * Cada mensagem é enviada a cada 2 segundos, com a primeira mensagem sendo enviada após 2 segundos de execução,
     * e as subsequentes com um atraso progressivo (4 segundos, 6 segundos, etc.).
     *
     * O envio das mensagens é realizado de maneira assíncrona para não bloquear a execução do programa.
     *
     * @param args Argumentos passados pela linha de comando (não utilizados neste caso).
     * @throws Exception Caso ocorra algum erro na execução.
     */
    @Override
    public void run(String... args) throws Exception {
        // Usando ScheduledExecutorService para agendar o envio de mensagens com intervalos
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Loop para enviar 40 mensagens
        for (int i = 1; i <= 40; i++) {
            final int messageId = i;

            // Agendar o envio da mensagem com delay de 2 segundos progressivos
            scheduler.schedule(() -> {
                // Envia a mensagem para o Kafka
                kafkaProducer.sendMessage("Mensagem número " + messageId);
                // Imprime uma mensagem no console indicando que a mensagem foi enviada com sucesso
                System.out.println("Mensagem " + messageId + " enviada com sucesso!");
            }, i * 2, TimeUnit.SECONDS);  // A cada 2 segundos após o envio da anterior
        }

        // Finaliza o scheduler de forma ordenada após o envio de todas as mensagens
        scheduler.shutdown();
    }
}
