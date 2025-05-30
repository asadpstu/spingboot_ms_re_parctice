package org.nightcrwaler.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobmsApplication.class, args);
        System.out.println("Jobms Application started");
    }

}
