package jpabook.jpashop;


import jpabook.jpashop.domain.*;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();

        ts.begin();

        try {

            Member1 member1 = new Member1();
            member1.setUsername("member1");
            em.persist(member1);

            Team1 team = new Team1();
            team.setName("teamA");
            team.getMembers().add(member1);

            em.persist(team);
            ts.commit();
        } catch (Exception e) {

        }
        finally {
            em.close();
        }
        emf.close();
    }
}
