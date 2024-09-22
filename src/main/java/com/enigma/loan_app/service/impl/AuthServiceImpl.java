package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.ERole;
import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.entity.AppUser;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.security.JwtUtil;
import com.enigma.loan_app.service.AuthService;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.RoleService;
import com.enigma.loan_app.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;

    private void validateEmail(String email) {
        if (userRepository.countByEmail(email) > 0) throw new ResponseStatusException(HttpStatus.CONFLICT, Message.EMAIL_ALREADY_EXIST);
    }

    private void validatePassword(String password, String confirmPassword) {
        if (confirmPassword == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.CONFIRM_PASSWORD_IS_REQUIRED);
        if (!password.equals(confirmPassword)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.CONFIRM_PASSWORD_DO_NOT_MATCH);
    }

    private User createUser(AuthRequest authRequest, List<Role> roles) {
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .roles(roles).build();
        return userRepository.saveAndFlush(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse signUpCustomer(AuthRequest authRequest) {
        validationUtil.validate(authRequest);
        validateEmail(authRequest.getEmail());
        validatePassword(authRequest.getPassword(), authRequest.getConfirmPassword());

        List<Role> roles = new ArrayList<>();
        Role roleCustomer = roleService.create(Role.builder().name(ERole.ROLE_CUSTOMER).build());
        roles.add(roleCustomer);

        User user = createUser(authRequest, roles);
        Customer customer = Customer.builder().user(user).build();
        customerService.create(customer);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(role -> role.getName().toString()).toList())
                .build();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse signUpAdmin(AuthRequest authRequest) {
        validationUtil.validate(authRequest);
        validateEmail(authRequest.getEmail());
        validatePassword(authRequest.getPassword(), authRequest.getConfirmPassword());

        List<Role> roles = new ArrayList<>();
        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        roles.add(roleAdmin);
        Role roleStaff = Role.builder().name(ERole.ROLE_STAFF).build();
        roles.add(roleStaff);

        User user = createUser(authRequest, roles);
        Customer customer = Customer.builder().user(user).build();
        customerService.create(customer);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(role -> role.getName().toString()).toList())
                .build();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public LoginResponse signIn(AuthRequest authRequest) {
        validationUtil.validate(authRequest);

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        AppUser appUser = (AppUser) authenticate.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .email(appUser.getEmail())
                .roles(appUser.getRoles().stream().map(Enum::name).toList())
                .token(token)
                .build();
    }


}
