package com.estudoapi.estudoapi.core.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Constraint(validatedBy = { SkillNameIsUniqueValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SkillNameIsUnique {
	String message() default "this ${validatedValue} skill name already exists";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
    

