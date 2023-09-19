package com.wf.training.onlinebankingsystem.repository;

import com.wf.training.onlinebankingsystem.model.AdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRequestRepository extends JpaRepository<AdminRequest, Long> {
    // You can add custom query methods here if needed
}
