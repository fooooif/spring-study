package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        Appconfig appconfig = new Appconfig();
//        MemberService memberService = appconfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);


        Member memberA = new Member(1L, "memberA", Grade.Vip);
        memberService.join(memberA);

        Member findmember = memberService.findMember(1L);

        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findmember.getName());
    }
}
