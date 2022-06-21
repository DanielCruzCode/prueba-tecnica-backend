package com.danielcruz.pruebatecnica.controllers.users.dto.response;

import com.danielcruz.pruebatecnica.domain.Bank;
import com.danielcruz.pruebatecnica.domain.Debt;
import com.danielcruz.pruebatecnica.domain.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Getter @Setter
public class UserBanksResDto extends UserResDto {

    private Set<Bank> banks;

    @NotNull
    public UserBanksResDto create(User user) {

        Set<Bank> bankList = user.getDebts()
                .stream()
                .map(this::getBankList)
                .collect(Collectors.toSet());

        UserBanksResDto userBanksDto = new UserBanksResDto();
        userBanksDto.setId(user.getId());
        userBanksDto.setUsername(user.getUsername());
        userBanksDto.setBanks(bankList);

        return userBanksDto;
    }

    private Bank getBankList(@NotNull Debt debt) {
        return debt.getBank();
    }
}
