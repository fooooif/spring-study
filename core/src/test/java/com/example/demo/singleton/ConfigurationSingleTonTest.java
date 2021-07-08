package com.example.demo.singleton;

import com.example.demo.Appconfig;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class ConfigurationSingleTonTest {

    @Test
    void configurationTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();

        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println(memberRepository);
        System.out.println(memberRepository1);
        System.out.println(memberRepository2);

        Assertions.assertThat(memberRepository).isSameAs(memberRepository1);
        Assertions.assertThat(memberRepository).isSameAs(memberRepository2);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
        Appconfig bean = ac.getBean(Appconfig.class);

        System.out.println(bean.getClass());
    }
}
