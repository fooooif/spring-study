package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

//
///**
// * 종 주문 2개
// * * userA
// * 	 * JPA1 BOOK
// * 	 * JPA2 BOOK
// * * userB
// * 	 * SPRING1 BOOK
// * 	 * SPRING2 BOOK
// */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1(){

            Member member =  createMember("UserA", "서울", "1", "1111");
            em.persist(member);
            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Book createBook(String s, int i, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setPrice(i);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member createMember(String userA, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(userA);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2(){

            Member member = createMember("UserB", "서울", "1", "1111");
            em.persist(member);
            Book book1 = createBook("SPRING1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("SRPING2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }
    }

}


