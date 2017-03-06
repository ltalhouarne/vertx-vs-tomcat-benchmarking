package com.ltalhouarne.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltalhouarne.vertx.server.StaticServer;
import io.vertx.core.Vertx;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@ComponentScan("com.ltalhouarne")
public class AppConfig {
    
    @Value("${kafka.topic}")
    private String topic;
    
    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    
    @Autowired
    private StaticServer staticServer;
    
    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);
    }
    
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    
    @Bean
    public KafkaTemplate kafkaTemplate() {
        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerProps()));
        kafkaTemplate.setDefaultTopic(topic);
        return kafkaTemplate;
    }

    private Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
