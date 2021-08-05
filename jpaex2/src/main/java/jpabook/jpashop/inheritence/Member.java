package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String name;

    //Period
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    @Embedded
    private Period workperiod;


    //주소.
//    private String city;
//    private String street;
//    private String zipcode;

    @Embedded
    private Address homeAddress;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "work_city")),
            @AttributeOverride(name="street",
                    column=@Column(name = "work_street")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "work_zipcode"))
    })
    private Address workAddress;

}
