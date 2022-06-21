package com.danielcruz.pruebatecnica.repository;

import com.danielcruz.pruebatecnica.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
