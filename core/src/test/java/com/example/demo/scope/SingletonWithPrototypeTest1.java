package com.example.demo.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        prototypeBean.addCount();
        assertThat(prototypeBean.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);


    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        //dependency lookup (DI가 아니다)

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();

            int count = prototypeBean.getCount();
            return count;
        }



    }
    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount() {

            return count;

        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);

        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}