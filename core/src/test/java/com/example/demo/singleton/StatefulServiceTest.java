package com.example.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;



public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자가 10000원 주문
        bean.order("userA", 10000);
        //ThreadB: B 사용자가 20000원 주문
        bean2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
//        int price = bean.getPrice();
//        System.out.println("price= " + price);

//        Assertions.assertThat(bean.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
