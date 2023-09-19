package com.wf.training.onlinebankingsystem.repository;

import com.wf.training.onlinebankingsystem.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // You can add custom query methods here if needed
}
