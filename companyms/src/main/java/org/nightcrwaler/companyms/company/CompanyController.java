package org.nightcrwaler.companyms.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

    @GetMapping
    public String root() {
        return "Company service is running";
    }

    @Value(value = "${custom.message}")
    private String message;

    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id){
        id = 1001L;
        return "Company id: " + id + " message: " + message;
    }

    @GetMapping("/tolerance")
    public String getToleranceInformation(){
        int companyCapital = (int) (Math.random() * 1000);
        return "Company capital is: " + companyCapital;
    }


}
