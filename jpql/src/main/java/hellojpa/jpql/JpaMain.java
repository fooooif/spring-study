package hellojpa.jpql;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class JpaMain {

    @Autowired
    EntityManager em;

    public void aa() {
        em.close();
        
    }



}
