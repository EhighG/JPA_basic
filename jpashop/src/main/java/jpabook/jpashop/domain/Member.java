package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// 자세한 제약조건 등을 적을지 말지는 선택. 그러나 DB를 왔다갔다 하지 않아도 된다는 면에서, 적는 게 좋은 듯?
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue // strategy 기본값 = auto
    @Column(name = "MEMBER_ID") // 형식은 회사 룰마다 다르다.
    private Long id;

//    @OneToMany(mappedBy = "member") // 연관관계를 적절히 끊어주는 게 더 좋은 설계
//    private List<Order> orders = new ArrayList<>();

    private String name;

    @Embedded // 생략가능
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
