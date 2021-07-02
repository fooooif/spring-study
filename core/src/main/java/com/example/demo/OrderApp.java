package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.order.Order;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrederServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrederServiceImpl();

        Member member = new Member(1L, "memberA", Grade.Vip);

        memberService.join(member);


        Order order = orderService.createOrder(1L, "ItemA",10000);

        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
}
