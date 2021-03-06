package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id") // 어디소속인지 알려주도록!   join할떄 알려주려고!!!! foreign키랑 맞추려고!!!
    private Long id;

    @ManyToOne(fetch = LAZY) // many to one/ont to one 모두 default =  EAGER
     @JoinColumn(name = "member_id")
    private Member member;

    //jpql select o From order o; -> SQL select * from order (n + 1) order 날리는 query가 다 보내짐!
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    // 원래는이렇게 g해야되는
    // persist(orderIteamA)
    // persist(orderIteamB)
    // persist(orderIteamC)
    // persist(order)

    // cascade 는
    // persist(order) 이것만으로 동작
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //자바 8에서 hibernate가 지원해줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status;// 주문상태 [Order,Cancle]

    //==연관관계 편의 메소드==/

    public void setMember(Member member) {
        this.member= member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);

    }


    //==생성 메소드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//

    /**
     * 주문 취소.
     */
    public void cancel(){
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCLE);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     * 전체 주문 가격 조회.
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
}
