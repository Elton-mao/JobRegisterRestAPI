package com.estudoapi.estudoapi.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudoapi.estudoapi.core.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Custom query methods can be defined here if needed
    // For example, to find jobs by type or level:
    // List<Job> findByType(JobType type);
    // List<Job> findByLevel(JobLevel level);
    
}
