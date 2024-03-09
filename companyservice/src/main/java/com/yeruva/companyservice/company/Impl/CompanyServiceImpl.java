package com.yeruva.companyservice.company.Impl;


import com.yeruva.companyservice.company.Company;
import com.yeruva.companyservice.company.CompanyRepository;
import com.yeruva.companyservice.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if (companyOptional.isPresent()) {
           Company company1 =companyOptional.get();
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            companyRepository.save(company1);
            return true;
        }
        return false;
    }

    public boolean deleteComapnyById(Long id){
        try {
            companyRepository.deleteById(id);
            return true;
            }
        catch(Exception e){
            return false;
        }
    }
    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }
}
