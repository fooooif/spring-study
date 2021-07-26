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
        //모든 데이터 변경은 트랜잭션 안에서 실행해야한다!!!
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        /**
         * 시작
         */
        transaction.begin();
        try {
            //비영속!
            Member member = new Member();
            member.setName("HelloJPA");
            member.setId(1L);
            //영속!

            em.persist(member);
            //준영속!
            em.detach(member);
            //삭제!
            em.remove(member);



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
