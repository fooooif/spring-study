package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // String으로 쓰기 순서에 의해서 밀리지 않음!!
    private DeliveryStatus status;//READY, COMP

}
