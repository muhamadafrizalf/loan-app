package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer findCustomerById(String id);
    List<Customer> findAllCustomers();
    void deleteCustomer(String id);
}
