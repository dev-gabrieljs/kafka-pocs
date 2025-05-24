package com.br.kafka_poc.component;

import com.br.kafka_poc.producer.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe responsável por enviar mensagens para o Kafka em intervalos programados.
 * A classe implementa CommandLineRunner, o que permite que o código seja executado
 * ao iniciar a aplicação Spring Boot.
 */
@Component
public class KafkaApplicationRunner implements CommandLineRunner {

    // Injeção de dependência do KafkaProducer para enviar mensagens para o Kafka
    private final KafkaProducer kafkaProducer;

    /**
     * Construtor da classe KafkaApplicationRunner. Inicializa o KafkaProducer.
     *
     * @param kafkaProducer O produtor de mensagens que será utilizado para enviar as mensagens ao Kafka.
     */
    public KafkaApplicationRunner(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Este método é chamado quando a aplicação Spring Boot é iniciada.
     * Ele programa o envio de 40 mensagens para o Kafka, com intervalos de 2 segundos entre cada envio.
     *
     * @param args Argumentos de linha de comando (não utilizados neste caso).
     * @throws Exception Caso ocorra algum erro durante a execução do método.
     */
    @Override
    public void run(String... args) throws Exception {
        // Criação de um agendador para executar tarefas em intervalos de tempo
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Loop para agendar o envio de 40 mensagens
        for (int i = 1; i <= 40; i++) {
            final int messageId = i;

            // Agendamento de cada mensagem para ser enviada após i * 2 segundos
            scheduler.schedule(() -> {
                // Envio da mensagem para o Kafka
                kafkaProducer.sendMessage("Mensagem número " + messageId);
                // Log da mensagem que foi enviada com sucesso
                System.out.println("Mensagem " + messageId + " enviada com sucesso!");
            }, i * 2, TimeUnit.SECONDS); // Intervalo de 2 segundos multiplicado pelo número da mensagem
        }

        // Desliga o agendador após as mensagens serem agendadas
        scheduler.shutdown();
    }
}
