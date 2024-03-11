package com.yeruva.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyserviceApplication.class, args);
	}

}
