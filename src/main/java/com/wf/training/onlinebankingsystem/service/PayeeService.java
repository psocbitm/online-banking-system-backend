package com.wf.training.onlinebankingsystem.service;

import com.wf.training.onlinebankingsystem.exception.PayeeNotFoundException;
import com.wf.training.onlinebankingsystem.model.Payee;
import com.wf.training.onlinebankingsystem.repository.PayeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayeeService {

    private final PayeeRepository payeeRepository;

    @Autowired
    public PayeeService(PayeeRepository payeeRepository) {
        this.payeeRepository = payeeRepository;
    }

    public List<Payee> getAllPayees() {
        return payeeRepository.findAll();
    }

    public Optional<Payee> getPayeeById(Long payeeId) {
        return payeeRepository.findById(payeeId);
    }

    public Payee createPayee(Payee payee) {
        return payeeRepository.save(payee);
    }

    public Payee updatePayee(Payee payee) {
        return payeeRepository.save(payee);
    }

    public void deletePayee(Long payeeId) {
        payeeRepository.deleteById(payeeId);
    }

    public void deleteAllPayees() {
        payeeRepository.deleteAll();
    }

    public Payee findPayeeByName(String payeeName) {
        return payeeRepository.findByName(payeeName)
                .orElseThrow(() -> new PayeeNotFoundException("Payee not found with name: " + payeeName));
    }
}
