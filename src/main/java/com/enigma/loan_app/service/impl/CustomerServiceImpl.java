package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EStatus;
import com.enigma.loan_app.constant.Message;
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

    private Customer getCustomerOrThrow(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.CUSTOMER_ID_IS_EMPTY);
        return customerRepository.findByIdAndStatus(id, EStatus.ACTIVE).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.CUSTOMER_NOT_FOUND));
    }

    private void validatePhone(String phone) {
        if (customerRepository.countByPhoneAndStatus(phone, EStatus.ACTIVE) > 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, Message.PHONE_ALREADY_EXIST);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Customer create(Customer customer) {
        customer.setStatus(EStatus.ACTIVE);
        return customerRepository.saveAndFlush(customer);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public Customer update(CustomerRequest customerRequest) {
        Customer customer = getCustomerOrThrow(customerRequest.getId());
        validatePhone(customerRequest.getPhone());

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhone(customerRequest.getPhone());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());

        return customerRepository.saveAndFlush(customer);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public Customer findById(String id) {
        return getCustomerOrThrow(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllAndStatus(EStatus.ACTIVE);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteById(String id) {
        Customer customer = getCustomerOrThrow(id);

        customer.setStatus(EStatus.INACTIVE);
        userService.delete(customer.getUser());
        customer.setUser(null);
        customerRepository.saveAndFlush(customer);
    }
}
