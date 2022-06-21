package com.danielcruz.pruebatecnica.repository;

import com.danielcruz.pruebatecnica.domain.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    @Query("SELECT d FROM Debt d WHERE d.user.id = ?1 AND d.bank.id = ?2 AND d.id = ?3")
    Optional<Debt> findDebtByUserBankAndDebtId(Long userId, Long bankId, Long debtId);
}