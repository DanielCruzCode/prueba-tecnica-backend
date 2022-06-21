package com.danielcruz.pruebatecnica.controllers.users;

import com.danielcruz.pruebatecnica.controllers.users.dto.request.PayFeesReqDto;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UserDebtResDto;
import com.danielcruz.pruebatecnica.domain.Debt;
import com.danielcruz.pruebatecnica.services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserPostController {

    private final DebtService debtService;

    @Autowired
    public UserPostController(DebtService debtService) {
        this.debtService = debtService;
    }

    @PutMapping(value = "/{userId}/banks", params = {"bank-id", "debt-id"})
    public ResponseEntity<?> payFees(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "bank-id") Long bankId,
            @RequestParam(name = "debt-id") Long debtId,
            @RequestBody PayFeesReqDto payFeesReq) {

        Map<String, Object> responseMessage = new HashMap<>();

        Optional<Debt> debtOptional = debtService.findDebtByUserBankAndDebtId(userId, bankId, debtId);

        if (debtOptional.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", String.format("The debt id %d don´t match with any record, or it don´t belongs to user %d id and bank %d", debtId, userId, bankId));
            return ResponseEntity.badRequest().body(responseMessage);
        }

        if (debtOptional.get().getFees() - payFeesReq.getFeesToPay() < 0) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", "The fees to pay cannot be more than current debt fees");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        Debt debtToUpdate = debtService.updatePaymentDebtInfo(debtOptional.get(), payFeesReq);
        Debt debtUpdated  = debtService.save(debtToUpdate);
        UserDebtResDto debtResDto = debtService.convertToDebtDto(debtUpdated);

        return new ResponseEntity<>(debtResDto, HttpStatus.ACCEPTED);
    }
}
