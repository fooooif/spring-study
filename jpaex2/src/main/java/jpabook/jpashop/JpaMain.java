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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setAge(10);
            member.setUsername("teamA");
            member.changeTeam(team);
            em.persist(member);





            em.flush();
            em.clear();
            String query = "select m,t " +
                            "from Member m left join m.team t where t.name = m.username";
            List resultList = em.createQuery(query)
                    .getResultList();

            for (Object o : resultList) {

            }
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
