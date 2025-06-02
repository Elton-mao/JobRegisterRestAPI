package com.estudoapi.estudoapi.config;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estudoapi.estudoapi.api.jobs.dtos.JobRequest;
import com.estudoapi.estudoapi.core.models.Job;
import com.estudoapi.estudoapi.core.models.Skill;
import com.estudoapi.estudoapi.core.repositories.SkillRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final SkillRepository skillRepository;  
    @Bean
    ModelMapper modelMapper(){
       var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(
        JobRequest.class,Job.class).addMappings(
            
            mapper -> mapper
            .using(toListOfSkills())
            .map(JobRequest::getSkills, Job::setSkills)
        );
       return modelMapper;
    }

    private Converter<List<Long>, List<Skill>> toListOfSkills(){
        return context -> skillRepository.findAllById(context.getSource());
    }
}
