package org.nightcrwaler.jobms.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface ICompanyClient {
    @GetMapping("/company/{id}")
    String getCompanyInformation(@PathVariable("id") Long id);

    @GetMapping("/company/tolerance")
    String getToleranceInformation();

}
