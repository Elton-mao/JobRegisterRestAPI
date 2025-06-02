package com.estudoapi.estudoapi.api.jobs.dtos;

import java.math.BigDecimal;

import com.estudoapi.estudoapi.core.enums.JobLevel;
import com.estudoapi.estudoapi.core.enums.JobType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
  
    private String title;

   
    private String description;

  
    private String company;

    private String location;


    private JobType jobType;

   
    private JobLevel jobLevel;

   
    private BigDecimal salary;
    
}
