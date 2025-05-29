package org.nightcrwaler.companyms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanymsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanymsApplication.class, args);
        System.out.println("Companyms Application started");
    }

}
