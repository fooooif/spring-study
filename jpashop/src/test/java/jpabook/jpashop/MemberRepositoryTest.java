package jpabook.jpashop;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    //entitymanger를 통한 데이터 변경은 트랜잭셔을 사용해야한다.
    @Test
    @Transactional//test케이스에 있으면 test가 끝나고 롤백
    @Rollback(value = false)
    public void testMember() throws Exception{
        //given

        Member_test member = new Member_test();
        member.setUsername("memberA");
        //when
        Long saveID = memberRepository.save(member);
        Member_test findMember = memberRepository.find(saveID);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // 저장한거랑 조회한거 같다라고 나온다. !!

    }
}