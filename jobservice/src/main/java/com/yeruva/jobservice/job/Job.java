package com.yeruva.jobservice.job;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor //optional...if we need it we can use this constrctor
@NoArgsConstructor //JPA NEEDS TO CREATE OBJECTS FOR ENTITIES (enities are objects..) so we need default no args constructor
@Getter
@Setter
@Entity
@Table(name="job_details")
public class Job {
    @Id
    @Column(name="job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
   //all remaining columns will get default column name swith varibale names
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;


    private Long companyId;





}
