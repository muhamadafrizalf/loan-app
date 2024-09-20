package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EStatus;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.repository.CustomerRepository;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer id cannot be empty");
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteCustomerById(String id) {
        Customer customer = findCustomerById(id);
        customer.setStatus(EStatus.NONACTIVE);
        userService.deleteUser(customer.getUser());
        customer.setUser(null);
        createCustomer(customer);
    }
}
