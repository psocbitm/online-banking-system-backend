package com.wf.training.onlinebankingsystem.controller;

import com.wf.training.onlinebankingsystem.model.AdminRequest;
import com.wf.training.onlinebankingsystem.service.AdminRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin-requests")
public class AdminRequestController {

    private final AdminRequestService adminRequestService;

    @Autowired
    public AdminRequestController(AdminRequestService adminRequestService) {
        this.adminRequestService = adminRequestService;
    }

    @GetMapping("/")
    public List<AdminRequest> getAllAdminRequests() {
        return adminRequestService.getAllAdminRequests();
    }

    @GetMapping("/{adminRequestId}")
    public Optional<AdminRequest> getAdminRequestById(@PathVariable Long adminRequestId) {
        return adminRequestService.getAdminRequestById(adminRequestId);
    }

    @PostMapping("/")
    public AdminRequest createAdminRequest(@RequestBody AdminRequest adminRequest) {
        return adminRequestService.createAdminRequest(adminRequest);
    }

    @PutMapping("/")
    public AdminRequest updateAdminRequest(@RequestBody AdminRequest adminRequest) {
        return adminRequestService.updateAdminRequest(adminRequest);
    }

    @DeleteMapping("/{adminRequestId}")
    public void deleteAdminRequest(@PathVariable Long adminRequestId) {
        adminRequestService.deleteAdminRequest(adminRequestId);
    }
}
