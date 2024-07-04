package hellojpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity { // 참고) 엔티티는, @Entity 나 @MappedSuperClass 가 붙은 클래스만 상속 가능하다.

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /* 지연 로딩이면, 둘 다 사용할 땐 쿼리가 2번 나가므로, 상황에 맞게 선택해야 한다.
    그러나, 실무에서는 지연 로딩만 사용하는 게 강력하게 권장된다! 이유는,
    1. 예상하지 못한 쿼리가 발생한다. (join으로 인한)
    2. 즉시 로딩은, JPQL에서 "N+1 문제"를 일으킨다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // JPA는 프록시 및 리플렉션을 사용하므로, 기본 생성자가 필요하다.
    public Member() {}
}
