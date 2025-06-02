package com.estudoapi.estudoapi.api.Skills.controllers;

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

import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillRequest;
import com.estudoapi.estudoapi.api.Skills.mappers.SkillMapper;
import com.estudoapi.estudoapi.core.exceptions.SkillNotFoundException;
import com.estudoapi.estudoapi.core.repositories.SkillRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillRestController {

    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;

    @GetMapping
    public List<SkillReSponse> findAll() {
        return skillRepository.findAll()
                .stream()
                .map(skillMapper::toSkillResponse)
                .toList();
        
    }

    @GetMapping("/{id}")
    public SkillReSponse findById(@PathVariable Long id) {
        return skillRepository.findById(id)
                .map(skillMapper::toSkillResponse)
                .orElseThrow(() -> new SkillNotFoundException());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SkillReSponse create(@Valid @RequestBody SkillRequest skillRequest){
        var skill = skillMapper.toSkill(skillRequest); 
        skillRepository.save(skill);
        return skillMapper.toSkillResponse(skill); 

    }

    @PutMapping("/{id}")
    public SkillReSponse update(@PathVariable Long id, @Valid @RequestBody SkillRequest skillRequest) {
        var skill = skillRepository.findById(id)
        .orElseThrow(SkillNotFoundException::new);
        BeanUtils.copyProperties(skillRequest, skill, "id");
        skillRepository.save(skill);
        return skillMapper.toSkillResponse(skill);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var skill = skillRepository.findById(id)
                .orElseThrow(SkillNotFoundException::new);
        skillRepository.delete(skill);
    }

}
