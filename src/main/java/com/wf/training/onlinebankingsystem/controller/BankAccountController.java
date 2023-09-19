package com.wf.training.onlinebankingsystem.controller;

import com.wf.training.onlinebankingsystem.exception.BankAccountCreationException;
import com.wf.training.onlinebankingsystem.exception.BankAccountNotFoundException;
import com.wf.training.onlinebankingsystem.exception.UserNotFoundException;
import com.wf.training.onlinebankingsystem.model.BankAccount;
import com.wf.training.onlinebankingsystem.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long accountId) {
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountById(accountId);
        return bankAccount.map(ResponseEntity::ok)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account not found with ID: " + accountId));
    }

    @PostMapping("/")
    public ResponseEntity<BankAccount> createBankAccount(
             @RequestBody BankAccount bankAccount,
            @RequestParam(name = "userId") Long userId) {
        try {
            BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBankAccount);
        } catch (UserNotFoundException ex) {
            throw new BankAccountCreationException(ex.getMessage());
        }
    }


    @PutMapping("/{accountId}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long accountId,
                                                         @RequestBody BankAccount bankAccount) {
        Optional<BankAccount> existingBankAccount = bankAccountService.getBankAccountById(accountId);
        if (existingBankAccount.isPresent()) {
            bankAccount.setAccountId(accountId);
            BankAccount updatedBankAccount = bankAccountService.updateBankAccount(bankAccount);
            return ResponseEntity.ok(updatedBankAccount);
        } else {
            throw new BankAccountNotFoundException("Bank Account not found with ID: " + accountId);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long accountId) {
        bankAccountService.deleteBankAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAllBankAccounts() {
        bankAccountService.deleteAllBankAccounts();
        return ResponseEntity.noContent().build();
    }
}
