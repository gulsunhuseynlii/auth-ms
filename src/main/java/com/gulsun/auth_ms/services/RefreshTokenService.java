package com.gulsun.auth_ms.services;

import com.gulsun.auth_ms.entity.RefreshToken;
import com.gulsun.auth_ms.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken saveRefreshToken(
            String email,
            String token) {

        RefreshToken refreshToken =
                RefreshToken.builder()
                        .email(email)
                        .token(token)
                        .expiryDate(
                                LocalDateTime.now()
                                        .plusDays(7)
                        )
                        .build();

        return refreshTokenRepository.save(
                refreshToken
        );
    }

    public RefreshToken findByToken(
            String token) {

        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Refresh token not found"
                        ));
    }
}
