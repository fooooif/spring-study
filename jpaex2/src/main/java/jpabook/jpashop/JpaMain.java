package jpabook.jpashop;



import jpabook.jpashop.inheritence.Book;
import jpabook.jpashop.inheritence.Member;
import jpabook.jpashop.inheritence.Movie;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();

        ts.begin();

        try {

            Member member1 = new Member();
            member1.setCreatedDate(LocalDateTime.now());
            member1.setLastModifiedBy("Aaa");
            em.persist(member1);

            Member member2 = new Member();
            member2.setCreatedDate(LocalDateTime.now());
            member2.setLastModifiedBy("ddd");
            em.persist(member2);
            em.flush();
            em.clear();
            /*
            getReference를 쓰면 query가 안나감. 가짜 클래스다!!
             */
//            Member reference = em.getReference(Member.class, member.getId());
            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            //타입 비교 == 으로 하지말기
            System.out.println("m1 == m2 " + (m1.getClass() == m2.getClass()));

            ts.commit();
        } catch (Exception e) {

        }
        finally {
            em.close();
        }
        emf.close();
    }

    private static void printMemberAndTeam(Member member) {

    }


}
