package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Integer countByEmail(String email);
}