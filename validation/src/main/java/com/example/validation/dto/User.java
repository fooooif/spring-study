package com.example.validation.dto;

import com.example.validation.annotation.Test_annotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
public class User {

    @NotBlank
    private String name;

    @Min(value = 0)
    @Max(value = 100)
    private int age;

    @Test_annotation
    private String birth_date;

}
