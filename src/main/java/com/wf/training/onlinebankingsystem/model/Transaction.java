package com.wf.training.onlinebankingsystem.model;

import com.wf.training.onlinebankingsystem.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private BankAccount sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private BankAccount targetAccount;
}
