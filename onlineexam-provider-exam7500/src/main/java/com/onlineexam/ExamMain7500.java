package com.onlineexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExamMain7500 {
    public static void main(String[] args) {
        SpringApplication.run(ExamMain7500.class,args);
    }
}
