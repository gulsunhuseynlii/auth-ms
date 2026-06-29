package com.gulsun.auth_ms.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String role;
}
