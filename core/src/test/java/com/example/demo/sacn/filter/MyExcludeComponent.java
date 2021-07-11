package com.example.demo.sacn.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // class level에 붙는거
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
