package com.danielcruz.pruebatecnica.controllers.users.dto.response;

import com.danielcruz.pruebatecnica.domain.Debt;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Getter
@Setter
public class UserDebtResDto extends UserResDto {

    private Long bankId;
    private Debt debt;

    @NotNull
    public UserDebtResDto create(Debt debt) {

        UserDebtResDto userDebtDto = new UserDebtResDto();
        userDebtDto.setId(debt.getUser().getId());
        userDebtDto.setUsername(debt.getUser().getUsername());
        userDebtDto.setBankId(debt.getBank().getId());
        userDebtDto.setDebt(debt);

        return userDebtDto;
    }
}
