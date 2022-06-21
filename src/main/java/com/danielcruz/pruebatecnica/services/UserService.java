package com.danielcruz.pruebatecnica.services;

import com.danielcruz.pruebatecnica.domain.User;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UserBanksResDto;
import com.danielcruz.pruebatecnica.controllers.users.dto.response.UsersResDto;
import com.danielcruz.pruebatecnica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository  userRepository;
    private final UserBanksResDto userBanksDto;
    private final UsersResDto     usersDto;

    @Autowired
    public UserService(UserRepository userRepository, UserBanksResDto userBanksDto, UsersResDto usersDto) {
        this.userRepository = userRepository;
        this.userBanksDto   = userBanksDto;
        this.usersDto       = usersDto;
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserBanksResDto convertToUserBankDto(User user) {
        return userBanksDto.create(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<UsersResDto> convertToUsersDtoList(List<User> users) {
        return users
                .stream()
                .map(usersDto::create)
                .collect(Collectors.toList());
    }

}
