package com.example.demo;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {


    //@Bean memberService -> new MemoryMemberReposiory();
    //@Bean orderService -> new MemoryMemberReposiory();
    @Bean
    public MemberService memberService(){
        System.out.println("Appconfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {

        System.out.println("Appconfig.meberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){

        System.out.println("Appconfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();

    }


}
