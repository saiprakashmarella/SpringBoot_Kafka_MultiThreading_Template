package com.myproject.domains.dto.kafka;

import com.myproject.domains.dto.Transaction;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionEvent implements Serializable {

    private String message;
    private String status;
    private Transaction transaction;

}
