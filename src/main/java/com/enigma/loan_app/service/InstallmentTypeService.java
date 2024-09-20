package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.InstallmentType;

import java.util.List;

public interface InstallmentTypeService {
    InstallmentType create(InstallmentType installmentType);
    InstallmentType findById(String id);
    List<InstallmentType> findAll();
    InstallmentType update(InstallmentType installmentType);
    void deleteById(String id);
}
