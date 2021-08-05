package jpabook.jpashop;



import jpabook.jpashop.inheritence.*;
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


            // side effect버그는 진짜 잡기 힘들다!!!!!
            // 복사를 해서 해야한다.!!!!!!!!!!!! 37번 줄!!!!!
            Member member = new Member();
            member.setName("member");
            member.setId(10000L);
            Address address = new Address("a", "b", "c");
            member.setHomeAddress(address);
            member.setWorkAddress(new Address("a", "b", "c"));
            member.setWorkperiod(new Period(LocalDateTime.now(),LocalDateTime.now()));
            em.persist(member);
            Member member1 = new Member();
            member.setName("member1");

            Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            member1.setHomeAddress(address1);
            member1.setWorkAddress(new Address("a", "b", "c"));
            member1.setWorkperiod(new Period(LocalDateTime.now(),LocalDateTime.now()));
            em.persist(member1);

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
