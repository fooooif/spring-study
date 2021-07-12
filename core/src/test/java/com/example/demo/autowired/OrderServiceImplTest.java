package com.example.demo.autowired;

import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;


public class OrderServiceImplTest {

    @Test
    void creatOrder(){
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.Vip));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
        orderService.createOrder(1L, "aa", 10000);

    }
}
