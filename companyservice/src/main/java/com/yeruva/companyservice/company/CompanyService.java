package com.yeruva.companyservice.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Long id,Company company);
 void createCompany(Company comapany);
  boolean deleteComapnyById(Long id);
  Company getCompanyById(Long id);
}
