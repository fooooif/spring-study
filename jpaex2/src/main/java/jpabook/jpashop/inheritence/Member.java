package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;





}
