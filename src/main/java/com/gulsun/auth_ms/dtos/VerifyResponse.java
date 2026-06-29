package com.gulsun.auth_ms.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyResponse {

    private boolean valid;
    private String email;
}
