package com.tujuhsembilan.scheduler.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.scheduler.model.Lokomotif;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducer {
    
    private KafkaTemplate<String, Lokomotif> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Lokomotif> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Lokomotif lokomotifData) {
        log.info(String.format("Message sent: %s ", lokomotifData.toString()));
        Message<Lokomotif> message = MessageBuilder
            .withPayload(lokomotifData)
            .setHeader(KafkaHeaders.TOPIC, "lokomotifdata")
            .build();
        kafkaTemplate.send(message);
    }

}
