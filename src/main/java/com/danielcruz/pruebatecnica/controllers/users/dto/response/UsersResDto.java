package com.danielcruz.pruebatecnica.controllers.users.dto.response;

import com.danielcruz.pruebatecnica.domain.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Getter @Setter
public class UsersResDto extends UserResDto {

    @NotNull
    public UsersResDto create(User user) {

        UsersResDto usersDto = new UsersResDto();
        usersDto.setId(user.getId());
        usersDto.setUsername(user.getUsername());

        return usersDto;
    }



}
