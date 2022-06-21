package com.danielcruz.pruebatecnica.services;

import com.danielcruz.pruebatecnica.controllers.users.dto.request.PayFeesReqDto;
import com.danielcruz.pruebatecnica.domain.Debt;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UserDebtResDto;
import com.danielcruz.pruebatecnica.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DebtService {

    private final DebtRepository debtRepository;
    private final UserDebtResDto userDebtDto;

    @Autowired
    public DebtService(DebtRepository debtRepository, UserDebtResDto userDebtDto) {
        this.debtRepository = debtRepository;
        this.userDebtDto    = userDebtDto;
    }

    public UserDebtResDto convertToDebtDto(Debt debt) {
        return userDebtDto.create(debt);
    }

    public Optional<Debt> findDebtByUserBankAndDebtId(Long userId, Long bankId, Long debtId) {
        return debtRepository.findDebtByUserBankAndDebtId(userId, bankId, debtId);
    }

    public Debt save(Debt debtToUpdate) {
        return debtRepository.save(debtToUpdate);
    }

    public Debt updatePaymentDebtInfo(Debt currentDebt, PayFeesReqDto payFeesReq) {
        Debt debtUpdate = new Debt();
        debtUpdate.setId(currentDebt.getId());
        debtUpdate.setDescription(currentDebt.getDescription());
        debtUpdate.setTotal(currentDebt.getTotal());
        debtUpdate.setFees(currentDebt.getFees());
        debtUpdate.setCurrency(currentDebt.getCurrency());
        debtUpdate.setUser(currentDebt.getUser());
        debtUpdate.setBank(currentDebt.getBank());

        if (payFeesReq.isPayAllFees() || currentDebt.getIsCanceled()) {
            debtUpdate.setTotalPayed(currentDebt.getTotal());
            debtUpdate.setRemainingFees(0);
            debtUpdate.setTotalRemaining(0F);
            debtUpdate.setIsCanceled(Boolean.TRUE);

            return debtUpdate;
        }

        debtUpdate.setTotalPayed(currentDebt.calculateTotalPayed(payFeesReq.getFeesToPay()));
        debtUpdate.setRemainingFees(currentDebt.getRemainingFees() - payFeesReq.getFeesToPay());
        debtUpdate.setTotalRemaining(currentDebt.calculateTotalRemaining());
        debtUpdate.setIsCanceled(currentDebt.calculateDebtCancellation());

        return debtUpdate;
    }

}
