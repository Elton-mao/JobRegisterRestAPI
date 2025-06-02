package com.estudoapi.estudoapi.api.jobs.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
import com.estudoapi.estudoapi.api.Skills.mappers.SkillMapper;
import com.estudoapi.estudoapi.core.exceptions.JobNotFoundException;
import com.estudoapi.estudoapi.core.repositories.JobRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{id}/jobs/skills")
public class JobSkillController {

    private final SkillMapper skillMapper;
    private final JobRepository jobRepository;

    public List<SkillReSponse> findSkillByJobId(@PathVariable Long id) {
        var job = jobRepository.findById(id)
                .orElseThrow(JobNotFoundException::new);
        return job.getSkills()
                .stream()
                .map(skillMapper::toSkillResponse)
                .toList();
    }
}
