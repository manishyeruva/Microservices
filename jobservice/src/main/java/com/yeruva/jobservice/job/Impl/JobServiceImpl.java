package com.yeruva.jobservice.job.Impl;


import com.yeruva.jobservice.job.Job;
import com.yeruva.jobservice.job.JobRepository;
import com.yeruva.jobservice.job.JobService;
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
    @Override
    public List<JobDto> findAll() {
        List<Job> jobs=jobRepository.findAll();
        return jobs.stream().map(this::converToDto).collect(Collectors.toList());
    }
    // how to use rest template... and way to write dto pattern
    private JobDto converToDto(Job job){
        // getforobject is used when we are getting and we know that reposne is single object/single details
        //exchange when we are getting response as list of repsonses
        //null ->specifies that we don't have any request body to send for this  request
//   new ParameterizedTypeReference<List<Review>>() ->this tells that we expecting, response in this format...list of reviews
            Company company=restTemplate.getForObject("http://COMPANY-SERVICE:8081/company/getCompany/"+job.getCompanyId(), Company.class);
           ResponseEntity<List<Review>> reviewResponse=
                   restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews/getAllReviews?companyId=" + job.getCompanyId(),
                    HttpMethod.GET, null,
                   new ParameterizedTypeReference<List<Review>>() {

                    });
           List<Review> reviews=reviewResponse.getBody();
            JobDto jobDto = JobMapper.mapToJobWithCompanyDto(job,company,reviews);
            return jobDto;
    }
    @Override
    public void CreateJob(Job job) {
        jobRepository.save(job);
    }
    public JobDto getJobById(Long id) {
        Job job =jobRepository.findById(id).orElse(null);
         return converToDto(job);
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
