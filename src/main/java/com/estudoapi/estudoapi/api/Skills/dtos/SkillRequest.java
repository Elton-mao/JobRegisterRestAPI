package com.estudoapi.estudoapi.api.Skills.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.estudoapi.estudoapi.core.validators.SkillNameIsUnique;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillRequest {
    
    @NotEmpty
    @SkillNameIsUnique
    private String name;
    
}
