package com.danielcruz.pruebatecnica.services;

import com.danielcruz.pruebatecnica.domain.Bank;
import com.danielcruz.pruebatecnica.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Optional<Bank> findBankById(Long bankId) {
        return bankRepository.findById(bankId);
    }

}
