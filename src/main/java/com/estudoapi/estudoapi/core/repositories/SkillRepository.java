package com.estudoapi.estudoapi.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudoapi.estudoapi.core.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    
boolean existsByName(String value);
boolean existsByNameAndIdNot(String name, Long id);
    
}
