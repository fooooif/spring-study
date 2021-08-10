package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


//
    //주소.
//    private String city;
//    private String street;
//    private String zipcode;

    @Embedded
    private Address homeAddress;

    //값타입 ㄱ컬렉션
    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "address", joinColumns =
        @JoinColumn(name = "member_id"))
    private List<Address> addressHistory = new ArrayList<>();

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city",
//                    column=@Column(name = "work_city")),
//            @AttributeOverride(name="street",
//                    column=@Column(name = "work_street")),
//            @AttributeOverride(name="zipcode",
//                    column=@Column(name = "work_zipcode"))
//    })
//    private Address workAddress;

}
