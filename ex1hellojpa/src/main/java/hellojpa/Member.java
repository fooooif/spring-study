package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity//jpa가 관리하는거라는 표시
//@Table(name = "MBR")
@SequenceGenerator(name = "member_seq_generator" ,
sequenceName = "member_seq")

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="member_seq_generator" )
    private Long id;


    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
