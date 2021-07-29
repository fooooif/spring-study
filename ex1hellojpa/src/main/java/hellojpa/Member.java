package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity//jpa가 관리하는거라는 표시
//@Table(name = "MBR")
@SequenceGenerator(name = "member_sequence_generate",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 50)

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence_generate")
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
