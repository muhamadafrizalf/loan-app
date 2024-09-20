package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.findById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message("Successfully retrieved customer")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .message("Successfully retrieved "+ customerList.size() +" customer(s)")
                .data(customerList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.update(customerRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message("Successfully updated customer")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message("Successfully deleted customer")
                .build();
        return ResponseEntity.ok(response);
    }


}
