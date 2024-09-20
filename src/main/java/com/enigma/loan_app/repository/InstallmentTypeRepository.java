package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.InstallmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentTypeRepository extends JpaRepository<InstallmentType, String> {
}
