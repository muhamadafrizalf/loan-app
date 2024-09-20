package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EInstallmentType;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.repository.InstallmentTypeRepository;
import com.enigma.loan_app.service.InstallmentTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstallmentTypeServiceImpl implements InstallmentTypeService {
    private final InstallmentTypeRepository installmentTypeRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public InstallmentType createInstallmentType(InstallmentType installmentType) {
        return installmentTypeRepository.saveAndFlush(installmentType);

    }

    @Override
    public InstallmentType findInstallmentTypeById(String id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Installment type id cannot be null or empty");
        return installmentTypeRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
