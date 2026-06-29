package com.gulsun.auth_ms.dtos;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
