package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EInstallmentType;
import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.repository.InstallmentTypeRepository;
import com.enigma.loan_app.service.InstallmentTypeService;
import com.enigma.loan_app.util.ValidationUtil;
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
    private final ValidationUtil validationUtil;

    private InstallmentType getInstallmentTypeOrThrow(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.INSTALLMENT_TYPE_ID_IS_EMPTY);
        return installmentTypeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.INSTALLMENT_TYPE_NOT_FOUND));
    }

    private void validateInstallmentType(EInstallmentType installmentType) {
        if (installmentTypeRepository.countByInstallmentType(installmentType) > 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, Message.INSTALLMENT_TYPE_ALREADY_EXIST);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public InstallmentType create(InstallmentType installmentType) {
        validationUtil.validate(installmentType);
        Optional<InstallmentType> optionalInstallmentType = installmentTypeRepository.findByInstallmentType(installmentType.getInstallmentType());
        return optionalInstallmentType.orElseGet(() -> installmentTypeRepository.saveAndFlush(installmentType));
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public InstallmentType findById(String id) {
        return getInstallmentTypeOrThrow(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<InstallmentType> findAll() {
        return installmentTypeRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public InstallmentType update(InstallmentType installmentType) {
        validationUtil.validate(installmentType);
        InstallmentType updatedInstallmentType = getInstallmentTypeOrThrow(installmentType.getId());
        validateInstallmentType(installmentType.getInstallmentType());
        updatedInstallmentType.setInstallmentType(installmentType.getInstallmentType());
        return installmentTypeRepository.saveAndFlush(updatedInstallmentType);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteById(String id) {
        getInstallmentTypeOrThrow(id);
        installmentTypeRepository.deleteById(id);
    }
}
