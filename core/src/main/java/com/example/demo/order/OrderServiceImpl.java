package com.example.demo.order;

import com.example.demo.discount.DiscountPolicy;

import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//스프링 빈에만 자동의존주입 가능!!


@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //필드 주입!!!!! not recmmend 밖에서 수정할 방법이 없으므로 setter 만들어줘야함!!!! 테스트 코드에서만 쓰는걸 권장함!
//    @Autowired private final MemberRepository memberRepository;
//    @Autowired private final DiscountPolicy discountPolicy;

    //수정자 주입!!!!! set메소드 사용!
//    @Autowired(required = false)
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }
//
//    // 일반 메서드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//        this.memberRepository = memberRepository;
//    }
    //생성자가 하나 있으면 Autowired 생략 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
