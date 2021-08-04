package jpabook.jpashop.inheritence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;





}
