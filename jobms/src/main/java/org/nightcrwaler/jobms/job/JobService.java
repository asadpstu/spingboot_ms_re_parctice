package org.nightcrwaler.jobms.job;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.nightcrwaler.jobms.feignclients.ICompanyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    ICompanyClient companyClient;

    int attempts = 0;

    @CircuitBreaker(
            name = "companyServiceBreaker",
            fallbackMethod = "getToleranceInformationFallback"
    )
    public String getTolerance() {
        return companyClient.getToleranceInformation();
    }


    @Retry(
            name = "companyServiceBreaker",
            fallbackMethod = "getToleranceInformationFallbackAfterRetry"
    )
    public String retry_with_fault_tolerance(){
        System.out.println("Total attempts: " + ++attempts);
        return companyClient.getToleranceInformation();
    }

    @RateLimiter(
            name = "companyServiceBreaker",
            fallbackMethod = "rateLimitFallback"
    )
    public String rate_limit(){
        return companyClient.getToleranceInformation();
    }


    private String getToleranceInformationFallback(Exception e) {
        return "Sorry, Company service is currently unavailable";
    }

    private String getToleranceInformationFallbackAfterRetry(Exception e) {
        return "Sorry, Our company service is currently unavailable after 10 retries.";
    }

    private String rateLimitFallback(Exception e) {
        return "Sorry, Please try again later.";
    }
}
