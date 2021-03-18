package com.onlineexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScoreMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(ScoreMain7001.class,args);
    }

}
