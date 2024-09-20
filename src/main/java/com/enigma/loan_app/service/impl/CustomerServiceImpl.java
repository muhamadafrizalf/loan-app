package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.Status;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.repository.CustomerRepository;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer updateCustomer(CustomerRequest customerRequest) {
        Customer customer = findCustomerById(customerRequest.getId());

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhone(customerRequest.getPhone());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());

        return createCustomer(customer);
    }

    @Override
    public Customer findCustomerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new RuntimeException();
        }
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        customer.setStatus(Status.NONACTIVE);
        userService.deleteUser(customer.getUser());
        customer.setUser(null);
        createCustomer(customer);
    }
}
