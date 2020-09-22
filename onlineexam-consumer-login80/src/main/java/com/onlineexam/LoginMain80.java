package com.onlineexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(LoginMain80.class,args);
    }
}
