package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        //로딩 시점에 딱 하나만 만들기!!!!!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //트랜잭션 단위에는 항상 entitymanager를 만들어야한다,!!!!
        //em 생성한거는 매우 간단하게 connection 받았다고 생각하면 된다.
        //쓰레드간의 공유x 사용하고 벼려야 한다
        //모든 데이터 변경은 트랜잭 션 안에서 실행해야한다!!!
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        /**
         * 시작
         */
        transaction.begin();
        try {
            Member member = new Member();
            member.setName("A");
            Member member1 = new Member();
            member1.setName("B");
            Member member2 = new Member();
            member2.setName("C");
            Member member3 = new Member();
            member3.setName("D");
            em.persist(member);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("============= ");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        finally {
            em.close();
        }

        emf.close();

    }
}
