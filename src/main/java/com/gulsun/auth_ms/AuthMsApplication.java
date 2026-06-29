package com.gulsun.auth_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.gulsun.auth_ms", "com.gulsun.auth_ms.config", "com.gulsun.auth_ms.security"})

public class AuthMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthMsApplication.class, args);
	}

}