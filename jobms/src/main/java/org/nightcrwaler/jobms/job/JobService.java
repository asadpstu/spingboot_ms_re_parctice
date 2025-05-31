package org.nightcrwaler.jobms.job;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.nightcrwaler.jobms.feignclients.ICompanyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    ICompanyClient companyClient;

    @CircuitBreaker(
            name = "companyServiceBreaker",
            fallbackMethod = "getToleranceInformationFallback"
    )
    public String getTolerance() {
        return companyClient.getToleranceInformation();
    }

    private String getToleranceInformationFallback(Exception e) {
        return "Sorry, Company service is currently unavailable";
    }
}
