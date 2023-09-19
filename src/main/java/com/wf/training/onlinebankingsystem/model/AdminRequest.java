package com.wf.training.onlinebankingsystem.model;

import com.wf.training.onlinebankingsystem.model.enums.AdminRequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AdminRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminRequestId;

    @ManyToOne
    private User user;

    private AdminRequestStatus status;
    private Date requestDate;
    private Date approvalDate;
}
