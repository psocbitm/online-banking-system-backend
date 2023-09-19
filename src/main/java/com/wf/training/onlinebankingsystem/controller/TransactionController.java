package com.wf.training.onlinebankingsystem.controller;

import com.wf.training.onlinebankingsystem.exception.InsufficientBalanceException;
import com.wf.training.onlinebankingsystem.model.Transaction;
import com.wf.training.onlinebankingsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {
        try {
            Transaction depositedTransaction = transactionService.deposit(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(depositedTransaction);
        } catch (InsufficientBalanceException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction) {
        try {
            Transaction withdrawnTransaction = transactionService.withdraw(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(withdrawnTransaction);
        } catch (InsufficientBalanceException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody Transaction transaction) {
        try {
            Transaction transferredTransaction = transactionService.transfer(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transferredTransaction);
        } catch (InsufficientBalanceException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
