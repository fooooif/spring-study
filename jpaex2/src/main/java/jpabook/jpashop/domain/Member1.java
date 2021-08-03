package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member1 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

//    @ManyToOne
//    @JoinColumn("TEAM_ID")
//    private Team1 team;


    private String username;

    //연과 관계 메소드
//    public void changeTeam(Team1 team) {
//        team.getMembers().add(this);
//        this.team = team;
//
//    }
}
