package com.sumup.drone_challenge.logic;

import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;

@Service
public class Validator {

    private final javax.validation.Validator validator;

    public Validator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(Object object){
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
