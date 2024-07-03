package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // order가 예약어인 DB가 있어서, 보통 얘만 ORDERS로 많이 쓴다.
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId; // 사용 시, memberId를 통해 member를 또 조회해야 하므로 비효율적이다.

    private LocalDateTime orderDate; // Springboot에선, 이름 관례를 직접 설정할 수 있다. 기본값은 Camel -> Snake

    @Enumerated(EnumType.STRING) // 필수!
    private OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
