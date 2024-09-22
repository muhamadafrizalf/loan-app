package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.ERole;
import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.entity.AppUser;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserById(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException(Message.USER_NOT_FOUND));
        List<ERole> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(Message.EMAIL_NOT_FOUND));
        List<ERole> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
