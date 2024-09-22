package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathApi.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(PathApi.ID)
    public ResponseEntity<?> getCustomerById(
            @PathVariable String id
    ) {
        Customer customer = customerService.findById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message(Message.CUSTOMER_FOUND)
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .message(Message.CUSTOMERS_FOUND(customerList.size()))
                .data(customerList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody CustomerRequest customerRequest
    ) {
        Customer customer = customerService.update(customerRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message(Message.CUSTOMER_UPDATED)
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(PathApi.ID)
    public ResponseEntity<?> deleteCustomer(
            @PathVariable String id
    ) {
        customerService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message(Message.CUSTOMER_DELETED)
                .build();
        return ResponseEntity.ok(response);
    }


}
