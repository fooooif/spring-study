package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class HelloLombok {

    private String name;
    private int age;


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setAge(1);
        helloLombok.setName("name");

        System.out.println(helloLombok.getAge());

    }
}
