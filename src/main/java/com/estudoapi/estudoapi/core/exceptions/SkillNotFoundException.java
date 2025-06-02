package com.estudoapi.estudoapi.core.exceptions;

public class SkillNotFoundException  extends ModelNotFoundExceptions{


    public SkillNotFoundException() {
        super("Skill not found with id: ");
    }

    public SkillNotFoundException(String message) {
        super(message);
    }
}
