package org.nightcrwaler.jobms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/job")
public class JobController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String root(){
        return "Job service is running";
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id){
        long companyId = 1001L;
        // RestTemplate restTemplate = new RestTemplate();
        String companyInfo = restTemplate.getForObject("http://COMPANY-SERVICE/company/" + companyId, String.class);
        return "This is Job details for : " +id+ " Company Information: " + companyInfo;
    }
}
