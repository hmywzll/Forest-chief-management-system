package com.forestchiefmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ForestChiefManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestChiefManagementSystemApplication.class, args);
    }

}
