package com.enigma.loan_app.repository;

import com.enigma.loan_app.constant.EStatus;
import com.enigma.loan_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Integer countByPhoneAndStatus(String phoneNumber, EStatus status);
}
