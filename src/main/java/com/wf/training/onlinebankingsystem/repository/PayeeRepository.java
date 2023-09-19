package com.wf.training.onlinebankingsystem.repository;

import com.wf.training.onlinebankingsystem.model.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {
    Optional<Payee> findByName(String payeeName);
}

