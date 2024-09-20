package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.request.InstallmentTypeRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.service.InstallmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/installment-type")
//@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
@RequiredArgsConstructor
public class InstallmentTypeController {
    private final InstallmentTypeService installmentTypeService;

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Ok");
    }

    @PostMapping
    public ResponseEntity<?> createInstallmentType(@RequestBody InstallmentType installmentType) {
        InstallmentType createdInstallmentType = installmentTypeService.createInstallmentType(installmentType);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message("Installment type created")
                .data(createdInstallmentType)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
