package com.estudoapi.estudoapi.api.jobs.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.estudoapi.estudoapi.api.jobs.dtos.JobRequest;
import com.estudoapi.estudoapi.api.jobs.dtos.JobResponse;
import com.estudoapi.estudoapi.core.models.Job;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MadelMapperJobMapper implements JobMapper{
    private final ModelMapper modelMapper; 
    
    @Override
    public JobResponse toJobResponse(Job job) {
        return modelMapper.map(job, JobResponse.class);
    }

    @Override
    public Job toJob(JobRequest jobRequest) {
        return modelMapper.map(jobRequest, Job.class);
    }
     
}
