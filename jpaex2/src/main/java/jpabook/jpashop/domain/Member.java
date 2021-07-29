package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "STREET")
    private String street;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //연관관계 메소드
    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);

    }
//    @Column(name = "TEAM_ID")
//    private Long teamId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

//    public void changeTeam(Team team){
//        this.team = team;
//        team.getMembers().add(this);
//
//    }
}
