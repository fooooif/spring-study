package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void save() {

        //given 주어졌을떄.
        Member member = new Member("kim", 10);
        //when  실행했을떄.
        memberRepository.save(member);
        //then  결과가 이거다.
        assertThat(memberRepository.findById(member.getId())).isEqualTo(member);

    }



    @Test
    void findAll() {
        //given
        Member member = new Member("kim", 20);
        Member member1 = new Member("kim", 21);
        Member member2 = new Member("kim", 22);
        //when
        Member save = memberRepository.save(member);
        Member save1 = memberRepository.save(member1);
        Member save2 = memberRepository.save(member2);
        //then
        List<Member> all = memberRepository.findAll();

        assertThat(all.size()).isEqualTo(3);
    }


}