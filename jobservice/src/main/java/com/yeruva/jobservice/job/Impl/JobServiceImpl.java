package com.yeruva.jobservice.job.Impl;


import com.yeruva.jobservice.job.Job;
import com.yeruva.jobservice.job.JobRepository;
import com.yeruva.jobservice.job.JobService;
import com.yeruva.jobservice.job.clients.CompanyClient;
import com.yeruva.jobservice.job.clients.ReviewClient;
import com.yeruva.jobservice.job.dto.JobDto;
import com.yeruva.jobservice.job.external.Company;
import com.yeruva.jobservice.job.external.Review;
import com.yeruva.jobservice.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;


    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;
    @Override
    public List<JobDto> findAll() {
        List<Job> jobs=jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    // how to use rest template... and way to write dto pattern
    private JobDto convertToDto(Job job){
            Company company=companyClient.getcompany(job.getCompanyId());
             List<Review> reviews=reviewClient.getReviews(job.getCompanyId());
            JobDto jobDto = JobMapper.mapToJobWithCompanyDto(job,company,reviews);
            return jobDto;
    }
    @Override
    public void CreateJob(Job job) {
        jobRepository.save(job);
    }
    public JobDto getJobById(Long id) {
        Job job =jobRepository.findById(id).orElse(null);
         return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean updateJob(Long id, Job updatedJob) {
    Optional<Job> jobOptional=jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job=jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setLocation((updatedJob.getLocation()));
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
