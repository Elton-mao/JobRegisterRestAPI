package com.estudoapi.estudoapi.api.jobs.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudoapi.estudoapi.api.jobs.dtos.JobRequest;
import com.estudoapi.estudoapi.api.jobs.dtos.JobResponse;
import com.estudoapi.estudoapi.api.jobs.mappers.JobMapper;
import com.estudoapi.estudoapi.core.exceptions.JobNotFoundException;
import com.estudoapi.estudoapi.core.repositories.JobRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")

@RequiredArgsConstructor
public class JobController {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @GetMapping
    public List<JobResponse> findAll() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toJobResponse)
                .toList();
    }

    @GetMapping("{id}")
    public JobResponse findByid(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(jobMapper::toJobResponse)
                .orElseThrow(JobNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public JobResponse create(@RequestBody @Valid JobRequest jobRequest) {
        var job = jobMapper.toJob(jobRequest);
        job = jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }

    @PutMapping("/{id}")
    public JobResponse update(@PathVariable Long id, @RequestBody @Valid JobRequest jobRequest) {
        var job = jobRepository.findById(id)
                .orElseThrow(JobNotFoundException::new);
        var jobData = jobMapper.toJob(jobRequest);
        BeanUtils.copyProperties(jobData, job, "id");
        job = jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        var job = jobRepository.findById(id)
                .orElseThrow(JobNotFoundException::new);
        jobRepository.delete(job);
    }



}
