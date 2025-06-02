package com.estudoapi.estudoapi.api.Skills.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillRequest;
import com.estudoapi.estudoapi.core.models.Skill;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelMapperSkillMaper implements SkillMapper {
    private final ModelMapper modelMapper; 

    @Override
    public Skill toSkill(SkillRequest skillRequest) {
        
        return modelMapper.map(skillRequest, Skill.class);
    }

    @Override
    public SkillReSponse toSkillResponse(Skill skill) {
        
        return modelMapper.map(skill, SkillReSponse.class);
    }

}
