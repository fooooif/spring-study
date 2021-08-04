package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Movie extends Item{

    private String director;
    private String actor;
}
