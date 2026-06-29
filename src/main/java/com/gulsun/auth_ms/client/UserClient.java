package com.gulsun.auth_ms.client;

import com.gulsun.auth_ms.dtos.CreateUser;
import com.gulsun.auth_ms.dtos.RegisterRequest;
import com.gulsun.auth_ms.dtos.UpdateUser;
import com.gulsun.auth_ms.dtos.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-client",
        url = "http://localhost:8082"
)
public interface UserClient {

    @PostMapping("/user/create")
    void register(
            @RequestBody RegisterRequest request
    );

    @GetMapping("/user/email/{email}")
    UserResponse getByEmail(
            @PathVariable String email
    );
    @PutMapping("/user/{id}")
    UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUser updateUser
    );
    @PostMapping("/user/create")
    UserResponse createUser(@RequestBody CreateUser createUser);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id);
}