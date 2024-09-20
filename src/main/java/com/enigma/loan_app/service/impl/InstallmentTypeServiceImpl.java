package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EInstallmentType;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.repository.InstallmentTypeRepository;
import com.enigma.loan_app.service.InstallmentTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstallmentTypeServiceImpl implements InstallmentTypeService {
    private final InstallmentTypeRepository installmentTypeRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public InstallmentType createInstallmentType(InstallmentType installmentType) {
        Optional<InstallmentType> optionalInstallmentType = installmentTypeRepository.findByInstallmentType(installmentType.getInstallmentType());
        return optionalInstallmentType.orElseGet(() -> installmentTypeRepository.saveAndFlush(installmentType));
    }

    @Override
    public InstallmentType findInstallmentTypeById(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Installment type id cannot be null or empty");
        return installmentTypeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Installment type not found"));
    }

    @Override
    public List<InstallmentType> findAllInstallmentTypes() {
        return installmentTypeRepository.findAll();
    }

    @Override
    public void deleteInstallmentTypeById(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Installment type id cannot be null or empty");
        installmentTypeRepository.deleteById(id);
    }
}
