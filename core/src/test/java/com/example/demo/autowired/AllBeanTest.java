package com.example.demo.autowired;

import com.example.demo.AutoAppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    
    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);

        DiscountService bean = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.Vip);
        int discountPrice = bean.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(bean).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(2000);

    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policies = policies;
            this.policyMap = policyMap;
            System.out.println(policyMap);
            System.out.println(policies);
        }

        public int discount(Member member, int i, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, i);
        }
    }
}
