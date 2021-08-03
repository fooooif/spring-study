package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Table(name = "ITEM2")
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;

}
