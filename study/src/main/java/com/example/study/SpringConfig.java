package com.example.study;

import com.example.study.repository.MemberRepository;
import com.example.study.repository.MemoryMemberReposiotry;
import com.example.study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberReposiotry();
    }
}
