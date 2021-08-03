package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private Long id;
    
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member2> member2s = new ArrayList<>()
}
