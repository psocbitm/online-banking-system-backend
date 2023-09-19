package com.wf.training.onlinebankingsystem.service;

import com.wf.training.onlinebankingsystem.exception.BankAccountCreationException;
import com.wf.training.onlinebankingsystem.exception.BankAccountNotFoundException;
import com.wf.training.onlinebankingsystem.exception.UserNotFoundException;
import com.wf.training.onlinebankingsystem.model.BankAccount;
import com.wf.training.onlinebankingsystem.model.User;
import com.wf.training.onlinebankingsystem.repository.BankAccountRepository;
import com.wf.training.onlinebankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Long accountId) {
        return bankAccountRepository.findById(accountId);
    }

    public BankAccount createBankAccount(BankAccount bankAccount, Long userId) {
        // Check if the associated user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        bankAccount.setUser(user);

        // Create the bank account
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long accountId) {
        bankAccountRepository.deleteById(accountId);
    }

    public void deleteAllBankAccounts() {
        bankAccountRepository.deleteAll();
    }
}
