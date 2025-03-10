package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.UserResponse;
import com.enigma.loan_app.entity.AppUser;
import com.enigma.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathApi.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(PathApi.ID)
    public ResponseEntity<?> getUserById(
            @PathVariable("id") String id
    ) {
        AppUser user = userService.loadUserById(id);
        UserResponse userResponse = UserResponse.builder()
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Enum::name).toList())
                .build();
        CommonResponse<UserResponse> response = CommonResponse.<UserResponse>builder()
                .message(Message.USER_FOUND)
                .data(userResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
