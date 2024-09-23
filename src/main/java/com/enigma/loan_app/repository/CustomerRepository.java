package com.enigma.loan_app.repository;

import com.enigma.loan_app.constant.EStatus;
import com.enigma.loan_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByIdAndStatus(String id, EStatus status);
    List<Customer> findByStatus(EStatus status);
    Integer countByPhoneAndStatus(String phoneNumber, EStatus status);
}
