package hellojpa.jpql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
