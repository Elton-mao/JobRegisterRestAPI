package com.estudoapi.estudoapi.api.Skills.mappers;

import com.estudoapi.estudoapi.api.Skills.dtos.SkillReSponse;
import com.estudoapi.estudoapi.api.Skills.dtos.SkillRequest;
import com.estudoapi.estudoapi.core.models.Skill;

public interface SkillMapper {
    Skill toSkill(SkillRequest skillRequest);
    SkillReSponse toSkillResponse(Skill skill);
}
