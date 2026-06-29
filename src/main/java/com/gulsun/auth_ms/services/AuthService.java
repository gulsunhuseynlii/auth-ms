package com.gulsun.auth_ms.services;

import com.gulsun.auth_ms.client.UserClient;
import com.gulsun.auth_ms.dtos.*;
import com.gulsun.auth_ms.entity.RefreshToken;
import com.gulsun.auth_ms.exceptions.InvalidCredentialsException;
import com.gulsun.auth_ms.exceptions.UserNotFoundException;
import com.gulsun.auth_ms.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final UserClient userClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public String register(RegisterRequest request) {

        log.info("Register request received for email: {}", request.getEmail());

        userClient.register(request);

        log.info("User registered successfully: {}", request.getEmail());

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {

        log.info("Login attempt for email: {}", request.getEmail());

        UserResponse user =
                userClient.getByEmail(
                        request.getEmail()
                );
        log.info("Email from DB: {}", user.getEmail());
        log.info("Password from DB: {}", user.getPassword());
        log.info(
                "Matches result: {}",
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        );
        if (user == null) {

            log.error("User not found: {}", request.getEmail());

            throw new UserNotFoundException(
                    "User not found"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            log.warn("Invalid credentials for email: {}",
                    request.getEmail());

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );


        log.info("User logged in successfully: {}",
                request.getEmail());
        String accessToken =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        String refreshToken =
                jwtService.generateRefreshToken(
                        user.getEmail()
                );

        refreshTokenService.saveRefreshToken(
                user.getEmail(),
                refreshToken
        );

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
    public RefreshTokenResponse refreshToken(
            RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.findByToken(
                        request.getRefreshToken()
                );

        UserResponse user =
                userClient.getByEmail(
                        refreshToken.getEmail()
                );

        String newAccessToken =
                jwtService.generateToken(
                        refreshToken.getEmail(),
                        user.getRole()
                );

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(
                        refreshToken.getToken()
                )
                .build();
    }

    public VerifyResponse verifyToken(String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return VerifyResponse.builder().valid(true).email(email).build();
    }
    public UserResponse updateUser(
            Long id,
            UpdateUser updateUser
    ) {

        return userClient.updateUser(
                id,
                updateUser
        );
    }
    public UserResponse createUser(CreateUser createUser){
        return  userClient.createUser(createUser);
    }

    public void deleteUser(Long id) {
        userClient.deleteUser(id);
    }
}