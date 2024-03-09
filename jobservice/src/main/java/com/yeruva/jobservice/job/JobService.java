package com.yeruva.jobservice.job;

import com.yeruva.jobservice.job.dto.JobDto;

import java.util.List;

public interface JobService {


    List<JobDto> findAll();

     void CreateJob(Job job);
    JobDto getJobById(Long id);

     boolean deleteJobById(Long id);
     boolean updateJob(Long id,Job updatedJob);
}
