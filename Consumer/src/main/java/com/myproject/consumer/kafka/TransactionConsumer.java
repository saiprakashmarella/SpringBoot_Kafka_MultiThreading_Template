package com.myproject.consumer.kafka;

import com.myproject.domains.dto.kafka.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(TransactionEvent transactionEvent) {
        log.info("Received transaction event {}", transactionEvent);
        transactionEvent.setMessage("Transaction Processed");
        transactionEvent.setStatus("Processed");
        // if we want , we can add DB operations also here like updating the transaction data to Processed
        log.info("Transaction with id processed successfully:{}", transactionEvent.getTransaction().getTransactionId());
    }

}
