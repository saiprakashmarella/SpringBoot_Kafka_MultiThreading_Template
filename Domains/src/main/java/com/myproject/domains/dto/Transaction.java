package com.myproject.domains.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Transaction implements Serializable {
    private String transactionId;
    private String userId;
    private String transactionType;
    private String transactionAmount;
    private LocalDate transactionDate;
}
