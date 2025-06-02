package com.estudoapi.estudoapi.core.validators;

import com.estudoapi.estudoapi.core.repositories.SkillRepository;
import com.estudoapi.estudoapi.core.services.HttpService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SkillNameIsUniqueValidator implements ConstraintValidator<SkillNameIsUnique, String> {
    private final SkillRepository skillRepository; 
    private final HttpService httpService; 

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null){
        return true; // null values are considered valid
      }
      var id = httpService.getPathVariable("id", Long.class);
      if (id.isPresent()){
         return !skillRepository.existsByName(value);
      }
       return !skillRepository.existsByNameAndIdNot(value, id.get());
    }

}
