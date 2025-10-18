package com.myproject.producer.service;

import com.myproject.domains.dto.Transaction;
import com.myproject.domains.dto.kafka.TransactionEvent;
import com.myproject.producer.config.AsyncConfig;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    @Value("${transactions.batch.size}")
    private int batchSize;

    private final AsyncConfig asyncConfig;
    private final TransactionProducerService transactionProducerService;

    public void postTransactions(List<Transaction> transactions) {
        log.info("Inside TransactionService::postTransactions");
        log.info("Converting the transactions of size :{} into Batches with size: {}", transactions.size(), batchSize);
        List<List<Transaction>> batches = convertIntoBatches(transactions);
        log.info("Number of batches: {}", batches.size());
        List<CompletableFuture<Void>> collect = batches.stream()
            .map(batch -> CompletableFuture.runAsync(() -> processTransactionBatch(batch),
                asyncConfig.asyncTransactionExecutor()))
            .toList();
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[0])).join();

    }

    private void processTransactionBatch(List<Transaction> transaction) {
        log.info("Inside TransactionService::processTransactionBatch");
        transaction.parallelStream().forEach(this::processTransaction);
    }

    private void processTransaction(Transaction transaction1) {
        log.info("Inside TransactionService::processTransaction");
        TransactionEvent transactionEvent = mapToTransactionEvent(transaction1);
        //we can add any other microservice api call here or any DB operations
        log.info("Converting transaction event to TransactionEvent : {}", transactionEvent);
        transactionProducerService.sendMessage(transactionEvent);
        log.info("Event was sent to Kafka consumers");
    }

    private TransactionEvent mapToTransactionEvent(Transaction transaction1) {
        return new TransactionEvent("transaction is in pending", "Pending", transaction1);
    }

    private List<List<Transaction>> convertIntoBatches(List<Transaction> transactions) {
        return ListUtils.partition(transactions, batchSize);
    }
}
