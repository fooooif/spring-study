package com.example.demo.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();
    }



    //객체 생성과 초기화(동작)는 따로 해야한다.!
    @Configuration
    static class LifeCycleConfig{
        @Bean(initMethod = "init",destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();//생성?
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
