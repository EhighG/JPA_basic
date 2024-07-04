package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 기본값 : 싱글 테이블
@DiscriminatorColumn // 하위 테이블 타입을 구분하는 컬럼이 추가되고, 기본 값들은 엔티티명이 된다.(JPA 구현체마다 다르다.)
//@DiscriminatorColumn(name = "DIS_TYPE") // 컬럼명 변경도 가능하다.
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
