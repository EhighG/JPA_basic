package jpabook.jpashop.domain;

import jakarta.persistence.*;

// 자세한 제약조건 등을 적을지 말지는 선택. 그러나 DB를 왔다갔다 하지 않아도 된다는 면에서, 적는 게 좋은 듯?
@Entity
public class Member {

    @Id @GeneratedValue // strategy 기본값 = auto
    @Column(name = "MEMBER_ID") // 형식은 회사 룰마다 다르다.
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
