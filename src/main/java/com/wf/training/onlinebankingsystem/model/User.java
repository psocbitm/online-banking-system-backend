package com.wf.training.onlinebankingsystem.model;


import com.wf.training.onlinebankingsystem.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String aadharCardNumber;
    private Date dateOfBirth;
    private String residentialAddress;
    private String permanentAddress;
    private String occupationalDetails;
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "user")
    private List<Payee> payees; // Add a list of payees
}
