package com.yeruva.companyservice.company;

import com.yeruva.companyservice.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Long id,Company company);
 void createCompany(Company comapany);
  boolean deleteComapnyById(Long id);
  Company getCompanyById(Long id);

  public void updateCompanyRating(ReviewMessage reviewMessage);
}
