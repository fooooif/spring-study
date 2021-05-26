package com.example.study.service;

import com.example.study.domain.Member;
import com.example.study.repository.MemoryMemberReposiotry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberReposiotry memberReposiotry ;

    @BeforeEach
    public void beforeEach(){
        memberReposiotry = new MemoryMemberReposiotry();
        memberService = new MemberService(memberReposiotry);
    }
    @AfterEach
    public void afterEach(){
        memberReposiotry.clearStore();
    }
    @Test
    void join() {
        //given

        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();

        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //them
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}