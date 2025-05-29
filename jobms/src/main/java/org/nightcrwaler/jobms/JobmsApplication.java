package org.nightcrwaler.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobmsApplication.class, args);
        System.out.println("Jobms Application started");
    }

}
