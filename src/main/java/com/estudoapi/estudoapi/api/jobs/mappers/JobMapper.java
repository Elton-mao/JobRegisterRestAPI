package com.estudoapi.estudoapi.api.jobs.mappers;

import com.estudoapi.estudoapi.api.jobs.dtos.JobRequest;
import com.estudoapi.estudoapi.api.jobs.dtos.JobResponse;
import com.estudoapi.estudoapi.core.models.Job;

public interface JobMapper {
    JobResponse toJobResponse(Job job);
    Job toJob(JobRequest jobRequest);
}
