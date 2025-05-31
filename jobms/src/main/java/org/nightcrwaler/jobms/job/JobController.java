package org.nightcrwaler.jobms.job;

import org.nightcrwaler.jobms.feignclients.ICompanyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/job")
public class JobController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ICompanyClient companyClient;

    @Autowired
    private JobService jobService;

    @GetMapping
    public String root(){
        return "Job service is running";
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id){
        long companyId = 1001L;
        // RestTemplate restTemplate = new RestTemplate();
        String companyInfo = restTemplate.getForObject("http://COMPANY-SERVICE/company/" + companyId, String.class);
        return "This is Job details for : " +id+ " Company Information by Rest Template: " + companyInfo;
    }

    @GetMapping("/feign/{id}")
    public String getJobByIdWithFeign(@PathVariable Long id){
        long companyId = 1001L;
        String companyInfo = companyClient.getCompanyInformation(companyId);
        return "This is Job details for : " +id+ " Company Information by FeignClient: " + companyInfo;
    }

    @GetMapping("/fault_tolerance")
    public String checkFaultTolerance(){
        return jobService.getTolerance();
    }

}
