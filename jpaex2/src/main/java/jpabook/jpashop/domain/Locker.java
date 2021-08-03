package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "LOCKER")
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCEK_ID")
    private Long id;


    private String name;

    @OneToOne(mappedBy = "locker") //자기는 locker가 주인이고 읽기 전용!!!!
    private Member2 member2;

}
