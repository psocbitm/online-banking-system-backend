package com.wf.training.onlinebankingsystem.controller;

import com.wf.training.onlinebankingsystem.exception.PayeeNotFoundException;
import com.wf.training.onlinebankingsystem.model.Payee;
import com.wf.training.onlinebankingsystem.service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payees")
public class PayeeController {

    private final PayeeService payeeService;

    @Autowired
    public PayeeController(PayeeService payeeService) {
        this.payeeService = payeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Payee>> getAllPayees() {
        List<Payee> payees = payeeService.getAllPayees();
        return ResponseEntity.ok(payees);
    }

    @GetMapping("/{payeeId}")
    public ResponseEntity<Payee> getPayeeById(@PathVariable Long payeeId) {
        Optional<Payee> payee = payeeService.getPayeeById(payeeId);
        return payee.map(ResponseEntity::ok)
                .orElseThrow(() -> new PayeeNotFoundException("Payee not found with ID: " + payeeId));
    }

    @PostMapping("/")
    public ResponseEntity<Payee> createPayee( @RequestBody Payee payee) {
        Payee createdPayee = payeeService.createPayee(payee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayee);
    }

    @PutMapping("/{payeeId}")
    public ResponseEntity<Payee> updatePayee(@PathVariable Long payeeId,
                                              @RequestBody Payee payee) {
        Optional<Payee> existingPayee = payeeService.getPayeeById(payeeId);
        if (existingPayee.isPresent()) {
            payee.setPayeeId(payeeId);
            Payee updatedPayee = payeeService.updatePayee(payee);
            return ResponseEntity.ok(updatedPayee);
        } else {
            throw new PayeeNotFoundException("Payee not found with ID: " + payeeId);
        }
    }

    @DeleteMapping("/{payeeId}")
    public ResponseEntity<Void> deletePayee(@PathVariable Long payeeId) {
        payeeService.deletePayee(payeeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAllPayees() {
        payeeService.deleteAllPayees();
        return ResponseEntity.noContent().build();
    }

    // Exception handling for PayeeNotFoundException
    @ExceptionHandler(PayeeNotFoundException.class)
    public ResponseEntity<String> handlePayeeNotFoundException(PayeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
