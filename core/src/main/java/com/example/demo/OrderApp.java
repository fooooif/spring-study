package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.order.Order;
import com.example.demo.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        Appconfig appconfig = new Appconfig();
//
//        MemberService memberService = appconfig.memberService();
//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = appconfig.orderService();
//        OrderService orderService = new OrederServiceImpl(null,null);


        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);// 구성정보 지정해주기 configuration

        MemberService memberService = ac.getBean("memberService" , MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        Member member = new Member(1L, "memberA", Grade.Vip);

        memberService.join(member);


        Order order = orderService.createOrder(1L, "ItemA",12000);

        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
}
