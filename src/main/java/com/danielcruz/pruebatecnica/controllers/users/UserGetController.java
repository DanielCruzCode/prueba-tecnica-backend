package com.danielcruz.pruebatecnica.controllers.users;

import com.danielcruz.pruebatecnica.domain.Bank;
import com.danielcruz.pruebatecnica.domain.Debt;
import com.danielcruz.pruebatecnica.domain.User;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UserBanksResDto;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UserDebtResDto;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UsersResDto;
import com.danielcruz.pruebatecnica.services.BankService;
import com.danielcruz.pruebatecnica.services.DebtService;
import com.danielcruz.pruebatecnica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserGetController {

    private final UserService userService;
    private final BankService bankService;
    private final DebtService debtService;

    @Autowired
    public UserGetController(UserService userService, BankService bankService, DebtService debtService) {
        this.userService = userService;
        this.bankService = bankService;
        this.debtService = debtService;
    }


    @GetMapping({"/", ""})
    public ResponseEntity<?> getAllUsers() {

        Map<String, Object> responseMessage = new HashMap<>();
        List<User> allUsers = userService.findAllUsers();

        if (allUsers.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", "The list of users is empty");
            return ResponseEntity.accepted().body(responseMessage);
        }

        List<UsersResDto> userList = userService.convertToUsersDtoList(allUsers);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping("/{userId}/banks")
    public ResponseEntity<?> getUserBanks(@PathVariable(name = "userId") Long userId) {

        Map<String, Object> responseMessage = new HashMap<>();

        Optional<User> user = userService.findUserById(userId);

        if (user.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", String.format("The user id %d don´t match with any record", userId));
            return ResponseEntity.badRequest().body(responseMessage);
        }

        UserBanksResDto userBanks = userService.convertToUserBankDto(user.get());

        return new ResponseEntity<>(userBanks, HttpStatus.FOUND);
    }


    @GetMapping(value = "/{userId}/banks", params = {"bank-id", "debt-id"})
    public ResponseEntity<?> getUserDebtById(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "bank-id") Long bankId,
            @RequestParam(name = "debt-id") Long debtId
            ) {

        Map<String, Object> responseMessage = new HashMap<>();

        Optional<User> userOptional = userService.findUserById(userId);
        Optional<Bank> bankOptional = bankService.findBankById(bankId);
        Optional<Debt> debtOptional = debtService.findDebtByUserBankAndDebtId(userId, bankId, debtId);

        if (userOptional.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", String.format("The user id %d don´t match with any record", userId));
            return ResponseEntity.badRequest().body(responseMessage);
        }
        if (bankOptional.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", String.format("The bank id %d don´t match with any record", bankId));
            return ResponseEntity.badRequest().body(responseMessage);
        }
        if (debtOptional.isEmpty()) {
            responseMessage.put("success", Boolean.FALSE);
            responseMessage.put("message", String.format("The debt id %d don´t match with any record, or it don´t belongs to user %d id and bank %d", debtId, userId, bankId));
            return ResponseEntity.badRequest().body(responseMessage);
        }

        UserDebtResDto userDebt = debtService.convertToDebtDto(debtOptional.get());

        return new ResponseEntity<>(userDebt, HttpStatus.FOUND);
    }
}
