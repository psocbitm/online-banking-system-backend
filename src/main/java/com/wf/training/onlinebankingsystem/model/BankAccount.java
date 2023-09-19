package com.wf.training.onlinebankingsystem.model;

import com.wf.training.onlinebankingsystem.model.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;

    @ManyToOne
    private User user;

    private Date createdDate;
}
