package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.service.InstallmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/installment-types")
@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
@RequiredArgsConstructor
public class InstallmentTypeController {
    private final InstallmentTypeService installmentTypeService;

    @PostMapping
    public ResponseEntity<?> createInstallmentType(@RequestBody InstallmentType installmentType) {
        InstallmentType createdInstallmentType = installmentTypeService.create(installmentType);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message("Installment type created")
                .data(createdInstallmentType)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstallmentTypeById(@PathVariable("id") String id) {
        InstallmentType installmentType = installmentTypeService.findById(id);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message("Successfully retrieved installment type")
                .data(installmentType)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllInstallmentTypes() {
        List<InstallmentType> installmentTypes = installmentTypeService.findAll();
        CommonResponse<List<InstallmentType>> response = CommonResponse.<List<InstallmentType>>builder()
                .message("Successfully retrieved " + installmentTypes.size() + " installment type(s)")
                .data(installmentTypes)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateInstallmentType(
            @RequestBody InstallmentType installmentType
    ) {
        InstallmentType updatedInstallmentType = installmentTypeService.update(installmentType);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message("Installment type updated")
                .data(updatedInstallmentType)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstallmentTypeById(@PathVariable("id") String id) {
        installmentTypeService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message("Installment type deleted")
                .build();
        return ResponseEntity.ok(response);
    }
}
