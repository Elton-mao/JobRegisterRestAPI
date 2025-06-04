package com.estudoapi.estudoapi.api.Skills.controllers;


import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudoapi.estudoapi.api.Skills.assemblers.SkillAssembler;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillRequest;
import com.estudoapi.estudoapi.api.Skills.mappers.SkillMapper;
import com.estudoapi.estudoapi.core.exceptions.SkillNotFoundException;
import com.estudoapi.estudoapi.core.repositories.SkillRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillRestController {

    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;
    private final SkillAssembler skillAssembler;

    @GetMapping
    public CollectionModel<EntityModel<SkillReSponse>> findAll() {
        var skills = skillRepository.findAll()
                .stream()
                .map(skillMapper::toSkillResponse)
                .toList();
        return  skillAssembler.toCollectionModel(skills); 
       

    }

    @GetMapping("/{id}")
    public EntityModel<SkillReSponse> findById(@PathVariable Long id) {
       var skill = skillRepository.findById(id)
                .map(skillMapper::toSkillResponse)
                .orElseThrow(() -> new SkillNotFoundException());
                return skillAssembler.toModel(skill);    
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<SkillReSponse> create(@Valid @RequestBody SkillRequest skillRequest) {
        var skill = skillMapper.toSkill(skillRequest);
        skillRepository.save(skill);
        var skillResponse = skillMapper.toSkillResponse(skill);
        return skillAssembler.toModel(skillResponse);

    }

    @PutMapping("/{id}")
    public EntityModel<SkillReSponse> update(@PathVariable Long id, @Valid @RequestBody SkillRequest skillRequest) {
        var skill = skillRepository.findById(id)
                .orElseThrow(SkillNotFoundException::new);
        BeanUtils.copyProperties(skillRequest, skill, "id");
        skillRepository.save(skill);
        var skillResponse = skillMapper.toSkillResponse(skill);
        return skillAssembler.toModel(skillResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var skill = skillRepository.findById(id)
                .orElseThrow(SkillNotFoundException::new);
        skillRepository.delete(skill);
        return ResponseEntity.noContent().build();
    }

}
