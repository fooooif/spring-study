package jpabook.jpashop;



import jpabook.jpashop.inheritence.Member;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
//            Member refMember = em.getReference(Member.class, member2.getId());
//            em.detach(refMember);
//
//
//            System.out.println(refMember.getCreatedBy());
            // 한 트랜잭션 안에서는 == 으로 해주면 true가 나와야 한다.!!!
//           System.out.println("m1 == m2 " + (m2 instanceof Member));


            Member reference = em.getReference(Member.class, member1.getId());

            Hibernate.initialize(reference); // 강제 초기화!!!
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(reference));
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

    private static void printMemberAndTeam(Member member) {

    }


}
