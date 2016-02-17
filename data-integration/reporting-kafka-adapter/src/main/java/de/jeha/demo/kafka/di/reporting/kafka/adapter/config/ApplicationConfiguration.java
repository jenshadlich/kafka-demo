package de.jeha.demo.kafka.di.reporting.kafka.adapter.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.kafka.support.KafkaProducerContext;
import org.springframework.integration.kafka.support.ProducerConfiguration;
import org.springframework.integration.kafka.support.ProducerMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author jenshadlich@googlemail.com
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(name = "inputToKafka")
    public PublishSubscribeChannel createPubSub() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public KafkaProducerContext createKafkaProducerContext() {
        final String topic = "test";
        final Map<String, ProducerConfiguration<?, ?>> producerConfigurations = new HashMap<>();

        final Serializer<String> serializer = new StringSerializer();
        final ProducerMetadata<String, String> producerMetadata =
                new ProducerMetadata<>(topic, String.class, String.class, serializer, serializer);

        final Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");

        final Producer<String, String> producer = new KafkaProducer<>(properties, serializer, serializer);
        final ProducerConfiguration<String, String> producerConfiguration =
                new ProducerConfiguration<>(producerMetadata, producer);
        producerConfigurations.put(topic, producerConfiguration);

        final KafkaProducerContext kafkaProducerContext = new KafkaProducerContext();
        kafkaProducerContext.setProducerConfigurations(producerConfigurations);

        return kafkaProducerContext;
    }

    @Bean
    public KafkaProducerMessageHandler createKafkaProducerMessageHandler(KafkaProducerContext kafkaProducerContext,
                                                                         @Qualifier("inputToKafka") PublishSubscribeChannel inputToKafka) {
        KafkaProducerMessageHandler handler = new KafkaProducerMessageHandler(kafkaProducerContext);
        handler.setOrder(1);

        inputToKafka.subscribe(handler);

        return handler;
    }
}
