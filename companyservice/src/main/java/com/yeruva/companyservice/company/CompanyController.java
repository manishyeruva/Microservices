package com.yeruva.companyservice.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @PostMapping("/createCompanies")
    public ResponseEntity<String> CreateCompanies(@RequestBody Company company){
        companyService.createCompany(company);
        return ResponseEntity.ok("Company Created successfully");
    }
    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable(value="id")Long id,@RequestBody Company company){
companyService.updateCompany(id,company);
        return  ResponseEntity.ok("company updated successfully");
    }

    @DeleteMapping("/deletCompany/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable(value="id") Long id){
        boolean isDeleted= companyService.deleteComapnyById(id);
        if(isDeleted){
            return new ResponseEntity<>("Company deleted Succesfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Company Exists with given id Not found to delete", HttpStatus.NOT_FOUND);

    }
    @GetMapping("/getCompany/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable (value="id") Long id){
        Company company=companyService.getCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
