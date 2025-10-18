package com.myproject.producer.service;

import com.myproject.domains.dto.kafka.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionProducerService {

    private final NewTopic topic;

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public void sendMessage(TransactionEvent transactionEvent) {
        log.info("Sending transaction event: {}", transactionEvent);
        Message<TransactionEvent> message = MessageBuilder
            .withPayload(transactionEvent)
            .setHeader(KafkaHeaders.TOPIC, topic.name())
            .build();
        log.info("Sending message to kafka : {}", message);
        kafkaTemplate.send(message);
    }

}
