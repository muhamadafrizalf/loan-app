package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.InstallmentType;

public interface InstallmentTypeService {
    InstallmentType createInstallmentType(InstallmentType installmentType);
    InstallmentType findInstallmentTypeById(String id);
}
