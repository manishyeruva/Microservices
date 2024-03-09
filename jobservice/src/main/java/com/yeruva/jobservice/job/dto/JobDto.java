package com.yeruva.jobservice.job.dto;

import com.yeruva.jobservice.job.external.Company;
import com.yeruva.jobservice.job.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDto {
    private Company company;
    private List<Review> reviews;
    private Long Id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

}
