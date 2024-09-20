package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.InstallmentType;

import java.util.List;

public interface InstallmentTypeService {
    InstallmentType createInstallmentType(InstallmentType installmentType);
    InstallmentType findInstallmentTypeById(String id);
    List<InstallmentType> findAllInstallmentTypes();
    void deleteInstallmentTypeById(String id);
}
