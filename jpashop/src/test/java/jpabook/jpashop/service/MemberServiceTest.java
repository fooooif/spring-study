package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // spring에서 transactional은 commit을 안하고 rollbback을 한
public class MemberServiceTest {

    //live template 참고한번 해보기!!!!!!!
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void join() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long joinId = memberService.join(member);


        //then
//        em.flush(); // em이 commity해준거.
        Assertions.assertEquals(member, memberRepository.findOne(joinId));
        }

    @Test(expected = IllegalStateException.class)
    public void duplicatejoin() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when

        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다!!!


        //then
        Assertions.fail("예외가 발생해야 한다.");
    }


}