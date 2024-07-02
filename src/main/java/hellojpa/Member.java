package hellojpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "MEMBER")
//@Table(uniqueConstraints = ) // unique 제약조건 걸 땐 이 방식을 더 자주 쓴다.
public class Member {

    @Id
    private Long id;

    @Column(name = "name", insertable = true, updatable = true, nullable = false) // nullable -> ddl 생성 시 not null 제약조건 붙음
//    @Column(columnDefinition = "varchar(100) default 'EMPTY'") // 컬럼을 직접 정의할 수 있다.
    private String name;

    @Column(unique = true) // 이렇게 하면 제약조건 이름이 랜덤 문자열이 되고, 복합키인 경우 적용할 수 없어 잘 안 쓴다.
    private Integer age;

////    @Column(precision = , scale = ) // 아주 큰 숫자나, 소수 등을 쓸 때 사용 가능한 속성들
//    private BigDecimal bigDecimal;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL 사용 시 주의! 값이 변경되면 기존에 값 순서 기반으로 저장됐던 데이터가 망가진다.(기존에 DB에 저장된 값은 변경되지 않으므로)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 자바의 Date 타입은 날짜 + 시간정보를 다 담지만, DB는 유형이 나뉘므로 맞추기 위해 사용
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP) // LocalDate, LocalDateTime 사용 시엔 생략 가능하다.
    private Date lastModifiedDate;

    private LocalDate testLocalDate; // date로 생성된다.

    private LocalDateTime testLocalDateTime; // timestamp로 생성된다.

    @Lob // varchar보다 긴 길이가 필요한 경우
    private String description;

    @Transient // DB랑 상관 없이, 애플리케이션 단에만 두고 싶은 데이터일 때(ex. 캐시) 사용
    private int temp;

    // JPA는 프록시 및 리플렉션을 사용하므로, 기본 생성자가 필요하다.
    public Member() {}
}
