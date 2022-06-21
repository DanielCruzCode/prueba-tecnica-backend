package com.danielcruz.pruebatecnica.controllers.users.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Getter
@Setter
public abstract class UserResDto {
    private    Long      id;
    private    String    username;

}
