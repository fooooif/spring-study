package jpabook.jpashop;
import jpabook.jpashop.inheritence.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();

        ts.begin();

        try {
            //자바 표준 스팩.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> from = query.from(Member.class);
            //동적쿼리 훨씬생각하기


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
