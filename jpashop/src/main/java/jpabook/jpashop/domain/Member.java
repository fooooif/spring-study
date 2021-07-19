package jpabook.jpashop.domain;

// N:N 관계는 1:N N:1 으로 풀어내라 단방향관계를 위주로 풀어내라!!!!
// 엔티티, 테이블 !!
//1:N 관계에서는 FK는 N에있다.
// 외래 키가 있는 곳을 연관관계의 주인으로 정해라!!!!!!


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //내가 맵핑되는거 읽기 전용! FK 바뀌지 않음
    private List<Order> orders = new ArrayList<>(); // best임 nullpointexception고려 안해도 됌! 변경하면 안된다.


}
