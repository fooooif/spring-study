package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
 public class Address {
    private String city;
    private String street;
    private String zeepcode;
}
