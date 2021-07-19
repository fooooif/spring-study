package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 자바 기본 생성자를 public or protected로 생성해야한다. 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문에
@Getter // 값은 변경이 되면 안된다. setter를 제공하지 않는다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //상속 받지 못하게. 함부러 new로 생성하지 못하게. jpa떄문에 생성한거다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
