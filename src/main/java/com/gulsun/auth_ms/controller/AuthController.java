package com.gulsun.auth_ms.controller;

import com.gulsun.auth_ms.dtos.*;
import com.gulsun.auth_ms.security.JwtService;
import com.gulsun.auth_ms.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
    @GetMapping("/verify")
    public ResponseEntity<VerifyResponse> verifyToken(
            @RequestHeader("Authorization") String authHeader
    ) {


        return ResponseEntity.ok(
               authService.verifyToken(authHeader)
        );
    }
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse>
    refreshToken(
            @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                authService.refreshToken(request)
        );
    }
    @PutMapping("/admin/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(authService.updateUser(id, updateUser)
        );
    }
    @PostMapping("/admin/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUser createUser){
        return ResponseEntity.ok(authService.createUser(createUser));
    }
    @DeleteMapping("/admin/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){ authService.deleteUser(id);
    }
}