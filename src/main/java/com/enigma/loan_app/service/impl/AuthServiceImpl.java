package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.ERole;
import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.service.AuthService;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    private User createUser(AuthRequest authRequest, List<Role> roles) {
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .roles(roles).build();
        return userRepository.saveAndFlush(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User signUpCustomer(AuthRequest authRequest) {


        List<Role> roles = new ArrayList<>();
        Role roleCustomer = roleService.getOrSaveRole(Role.builder().name(ERole.ROLE_CUSTOMER).build());
        roles.add(roleCustomer);
        User user = createUser(authRequest, roles);
        Customer customer = Customer.builder().user(user).build();
        customerService.createCustomer(customer);

        return user;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User signUpAdmin(AuthRequest authRequest) {

        List<Role> roles = new ArrayList<>();
        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        Role roleStaff = Role.builder().name(ERole.ROLE_STAFF).build();
        roles.add(roleAdmin);
        roles.add(roleStaff);
        User user = createUser(authRequest, roles);
        Customer customer = Customer.builder().user(user).build();
        customerService.createCustomer(customer);

        return user;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User signIn(AuthRequest authRequest) {
        return null;
    }


}
