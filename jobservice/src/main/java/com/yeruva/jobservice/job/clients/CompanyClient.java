package com.yeruva.jobservice.job.clients;

import com.yeruva.jobservice.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {

        @GetMapping("company/getCompany/{id}")
        public Company getcompany(@PathVariable("id") Long companyId);
}
