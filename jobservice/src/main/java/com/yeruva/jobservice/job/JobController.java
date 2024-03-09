package com.yeruva.jobservice.job;

import com.yeruva.jobservice.job.dto.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;
    private List<Job> jobs=new ArrayList<>();

    //IOC DI.. we can do it by like this or by autoconfiguring
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/findAllJobs")
    public ResponseEntity<List<JobDto>> findAll(){
     return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping("/createJobs")
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobService.CreateJob(job);
       // Company c=job.getComp();
        return new ResponseEntity<>("Job profile has been added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/findJobById/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable(value = "id") Long id){
        JobDto jobDto =jobService.getJobById(id);
        if(jobDto !=null)
            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteJobById/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable(value="id") Long id){
       boolean delete=jobService.deleteJobById(id);
       if(delete)
           return ResponseEntity.ok("JOb DELETED SUCCESSFULLY");
       return new ResponseEntity<>("JOB NOT FOUND WITH GIVEN ID TO DELETE",
               HttpStatus.NOT_FOUND);
    }

   // @PutMapping("/updateJobById/{id}") request can written in both ways
    @RequestMapping(value="/updateJobById/{id}",method=RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable(value="id") Long id,
                                            @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);

        if(updated)
            return ResponseEntity.ok("Job updated successfully");
        return new ResponseEntity<>("NO JOB FOUND WITH GIVEN ID",HttpStatus.NOT_FOUND);
    }
}
