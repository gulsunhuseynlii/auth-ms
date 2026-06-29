package com.gulsun.auth_ms.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private String username;
    private String email;
}