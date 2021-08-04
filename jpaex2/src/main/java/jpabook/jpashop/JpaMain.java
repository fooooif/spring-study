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
            Book book = new Book();
            book.setName("jpa");
            book.setAuthor("안녕");
            em.persist(book);

            ts.commit();
        } catch (Exception e) {

        }
        finally {
            em.close();
        }
        emf.close();
    }
}
