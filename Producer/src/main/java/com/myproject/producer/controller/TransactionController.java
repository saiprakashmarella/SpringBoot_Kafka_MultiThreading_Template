package com.myproject.producer.controller;

import com.myproject.domains.dto.Transaction;
import com.myproject.producer.service.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/postTransactions")
    public ResponseEntity<String> postTransactions(@RequestBody List<Transaction> transactions) {
        log.info("Inside TransactionController::postTransactions");
        transactionService.postTransactions(transactions);
        return ResponseEntity.ok().body("Transactions posted successfully");
    }



}
