package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)  // 직접 구현한 configuration 뺴기 위해서 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 떄문에 예제 코드 살리기 위해!!
public class AutoAppConfig {

}
