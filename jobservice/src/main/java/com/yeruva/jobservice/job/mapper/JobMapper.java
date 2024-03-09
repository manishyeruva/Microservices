package com.yeruva.jobservice.job.mapper;

import com.yeruva.jobservice.job.Job;
import com.yeruva.jobservice.job.dto.JobDto;
import com.yeruva.jobservice.job.external.Company;
import com.yeruva.jobservice.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDto mapToJobWithCompanyDto(
            Job job, Company company, List<Review> reviews)
    {
        JobDto jobDto =new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setLocation(job.getLocation());
        jobDto.setDescription(job.getDescription());
        jobDto.setCompany(company);
        jobDto.setReviews(reviews);
        return jobDto;
    }
}
