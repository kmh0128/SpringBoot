package com.shop.entity;


import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //1
@Getter
@Setter
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //2

    private LocalDateTime orderDate; //물건 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //1,3
    private List<OrderItem> orderItems = new ArrayList<>(); //2

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public void addOrderItem(OrderItem orderItem) {//3
        orderItems.add(orderItem);
        orderItem.setOrder(this);//4
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);//5
        for(OrderItem orderItem : orderItemList) {//6
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);//7
        order.setOrderDate(LocalDateTime.now());//8
        return order;
    }

    public int getTotalPrice() {//9
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

}

/*
1. 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이하는 CascadeTypeAll 옵션을 설정하겠습니다.


 */

/*
orphanRemoval = true -> 고아객체 삭제를 담당하는 역할
 */

/*
3 -> orderItems에는 주문 상품 정보들을 담아줍니다.

orderItem 객체를 order 객체의 orderItems에 추가합니다

4-> Order 엔티티와 OrderItem 엔티티가 양방향 참조 관계 이므로, orderItem 객체에도 order 객체를 세팅합니다.

5 -> 상품을 주문한 회원의 정보를 세팅합니다.

6 -> 상품 페이지에서는 1개의 상품을 주문하지만, 장바구니 페이지에서는 한 번에 여러개의 상품을 주문할수 있다.

따라서 여러 개의 주문 상품을 담을 수 있도록 리스트형태로 파라미터 값을 받으며 주문 객체에 orderItem 객체를 추가합니다.

7-> 주문시간을 ORDER로 세팅

8-> 현재 시간을 주문시간으로 세팅한다

9 총 주문 금액을 구하는 메소드

 */


//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
//https://dev-elop.tistory.com/entry/JPA-orphanRemoval-%EC%9A%A9%EB%8F%84