package jpabook.jpashop.testdomain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /*
    category가 쭉 밑으로 내려가는데 그것을 보여주는 것!!!!
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    //내가 조인하는거 joinColumns
    //상대편이 조인하는거 inverseJoinColumns
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns =  @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();
}
