package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.repository.RoleRepository;
import com.enigma.loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        Optional<Role> roleOptional = roleRepository.findByName(role.getName());
        return roleOptional.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}