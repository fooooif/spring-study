package com.example.validation.validator;

import com.example.validation.annotation.Test_annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test_annotationValidator implements ConstraintValidator<Test_annotation, String> {

    private String pattern;

    @Override
    public void initialize(Test_annotation constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
