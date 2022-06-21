package com.danielcruz.pruebatecnica.repository;

import com.danielcruz.pruebatecnica.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
