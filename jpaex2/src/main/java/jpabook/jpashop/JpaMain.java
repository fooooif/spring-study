package jpabook.jpashop;



import jpabook.jpashop.inheritence.Movie;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();

        ts.begin();

        try {
            Movie movie = new Movie();
            movie.setActor("actor1");
            movie.setDirector("director1");
            movie.setName("바람과 함꼐사라지다.");
            movie.setPrice(10000);
            em.persist(movie);
            ts.commit();
        } catch (Exception e) {

        }
        finally {
            em.close();
        }
        emf.close();
    }
}
