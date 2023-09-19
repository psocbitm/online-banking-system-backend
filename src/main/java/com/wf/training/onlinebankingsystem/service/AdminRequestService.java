package com.wf.training.onlinebankingsystem.service;
import com.wf.training.onlinebankingsystem.model.AdminRequest;
import com.wf.training.onlinebankingsystem.repository.AdminRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminRequestService {
    private final AdminRequestRepository adminRequestRepository;

    @Autowired
    public AdminRequestService(AdminRequestRepository adminRequestRepository) {
        this.adminRequestRepository = adminRequestRepository;
    }

    public List<AdminRequest> getAllAdminRequests() {
        return adminRequestRepository.findAll();
    }

    public Optional<AdminRequest> getAdminRequestById(Long adminRequestId) {
        return adminRequestRepository.findById(adminRequestId);
    }

    public AdminRequest createAdminRequest(AdminRequest adminRequest) {
        return adminRequestRepository.save(adminRequest);
    }

    public AdminRequest updateAdminRequest(AdminRequest adminRequest) {
        return adminRequestRepository.save(adminRequest);
    }

    public void deleteAdminRequest(Long adminRequestId) {
        adminRequestRepository.deleteById(adminRequestId);
    }

    // Add more business logic methods as needed
}

