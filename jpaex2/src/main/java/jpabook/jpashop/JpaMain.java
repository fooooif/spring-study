package jpabook.jpashop;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.MemberDto;
import jpabook.jpashop.domain.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();

        ts.begin();

        try {
            Member member = new Member();
            member.setAge(10);
            member.setUsername("member1");

            em.persist(member);
            em.flush();
            em.clear();

            System.out.println("========================================");
            List<MemberDto> resultList = em.createQuery("select new jpabook.jpashop.domain.MemberDto(m.username,m.age)  from Member m ", MemberDto.class)
                    .getResultList();
            System.out.println("========================================");

            MemberDto memberDto = resultList.get(0);
            System.out.println(memberDto.getAge());

//            Object o = resultList.get(0);
//            Object[] ob = (Object[]) o;
//            for (Object o1 : ob) {
//                System.out.println("o1 = " + o1);
//            }
//



            ts.commit();
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        emf.close();
    }




}
