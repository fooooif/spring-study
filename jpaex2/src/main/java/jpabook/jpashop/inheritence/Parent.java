package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
