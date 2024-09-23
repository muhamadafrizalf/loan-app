package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.service.InstallmentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathApi.INSTALLMENT_TYPE)
@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
@RequiredArgsConstructor
public class InstallmentTypeController {
    private final InstallmentTypeService installmentTypeService;

    @PostMapping
    public ResponseEntity<?> createInstallmentType(
            @Valid @RequestBody InstallmentType installmentType
    ) {
        InstallmentType createdInstallmentType = installmentTypeService.create(installmentType);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message(Message.INSTALLMENT_TYPE_CREATED)
                .data(createdInstallmentType)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(PathApi.ID)
    public ResponseEntity<?> getInstallmentTypeById(
            @PathVariable("id") String id
    ) {
        InstallmentType installmentType = installmentTypeService.findById(id);
        CommonResponse<InstallmentType> response = CommonResponse.<InstallmentType>builder()
                .message(Message.INSTALLMENT_TYPE_FOUND)
                .data(installmentType)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllInstallmentTypes() {
        List<InstallmentType> installmentTypes = installmentTypeService.findAll();
        CommonResponse<List<InstallmentType>> response = CommonResponse.<List<InstallmentType>>builder()
                .message(Message.INSTALLMENT_TYPE_FOUNDS(installmentTypes.size()))
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
                .message(Message.INSTALLMENT_TYPE_UPDATED)
                .data(updatedInstallmentType)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(PathApi.ID)
    public ResponseEntity<?> deleteInstallmentTypeById(
            @PathVariable("id") String id
    ) {
        installmentTypeService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message(Message.INSTALLMENT_TYPE_DELETED)
                .build();
        return ResponseEntity.ok(response);
    }
}
