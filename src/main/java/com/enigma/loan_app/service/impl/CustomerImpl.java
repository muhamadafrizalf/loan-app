package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.repository.CustomerRepository;
import com.enigma.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer findCustomerById(String id) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return List.of();
    }

    @Override
    public void deleteCustomer(String id) {

    }
}
