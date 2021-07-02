package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.Vip);
        memberService.join(memberA);

        Member findmember = memberService.findMember(1L);

        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findmember.getName());
    }
}
