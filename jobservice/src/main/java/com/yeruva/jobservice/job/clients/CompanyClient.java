package com.yeruva.jobservice.job.clients;

import com.yeruva.jobservice.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {

         @GetMapping("")
        public Company getcompany(Long companyId);
}
