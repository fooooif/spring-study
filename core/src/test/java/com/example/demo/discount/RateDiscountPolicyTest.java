package com.example.demo.discount;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L,"memberVip", Grade.Vip);

        //when

        int dis_price = discountPolicy.discount(member, 12000);

        //then

        assertThat(dis_price).isEqualTo(1200);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        //given
        Member member = new Member(1L,"memberVip", Grade.Basic);

        //when

        int dis_price = discountPolicy.discount(member, 12000);

        //then

        assertThat(dis_price).isEqualTo(0);
    }
}