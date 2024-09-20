package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.AppUser;
import com.enigma.loan_app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);
    void delete(User user);
}
