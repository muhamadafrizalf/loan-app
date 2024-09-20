package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer update(CustomerRequest customerRequest);
    Customer findById(String id);
    List<Customer> findAll();
    void deleteById(String id);
}
