package com.security.SJWT;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SecurityAuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityAuth.class, args);
	}

}
