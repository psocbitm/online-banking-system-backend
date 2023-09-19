package com.wf.training.onlinebankingsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payeeId;

    private String name;
    private String accountNumber;
    private String nickname;

    @ManyToOne
    private User user;
}
