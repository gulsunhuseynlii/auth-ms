package com.gulsun.auth_ms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    private String username;
    private String email;
    private String password;
    private Long roleId;
}
