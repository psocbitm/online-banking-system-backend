package com.wf.training.onlinebankingsystem.service;

import com.wf.training.onlinebankingsystem.exception.InsufficientBalanceException;
import com.wf.training.onlinebankingsystem.model.BankAccount;
import com.wf.training.onlinebankingsystem.model.Transaction;
import com.wf.training.onlinebankingsystem.model.enums.TransactionType;
import com.wf.training.onlinebankingsystem.repository.BankAccountRepository;
import com.wf.training.onlinebankingsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction deposit(Transaction transaction) {
        BankAccount targetAccount = transaction.getTargetAccount();
        BigDecimal amount = transaction.getAmount();

        // Add the amount to the target account
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        bankAccountRepository.save(targetAccount);

        // Create and save the transaction
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction withdraw(Transaction transaction) {
        BankAccount sourceAccount = transaction.getSourceAccount();
        BigDecimal amount = transaction.getAmount();

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }

        // Deduct the amount from the source account
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        bankAccountRepository.save(sourceAccount);

        // Create and save the transaction
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transfer(Transaction transaction) {
        BankAccount sourceAccount = transaction.getSourceAccount();
        BankAccount targetAccount = transaction.getTargetAccount();
        BigDecimal amount = transaction.getAmount();

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for transfer");
        }

        // Deduct the amount from the source account
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        bankAccountRepository.save(sourceAccount);

        // Add the amount to the target account
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        bankAccountRepository.save(targetAccount);

        // Create and save the transaction
        transaction.setTransactionType(TransactionType.TRANSFER);
        return transactionRepository.save(transaction);
    }
}
