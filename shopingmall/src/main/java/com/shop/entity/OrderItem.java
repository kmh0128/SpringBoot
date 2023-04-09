package com.shop.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{ //1

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //2

    private int orderPrice; // 주문가격

    private int count; // 수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);//1+
        orderItem.setCount(count);//2
        orderItem.setOrderPrice(item.getPrice());//3
        item.removeStock(count);//4
        return orderItem;
    }

    public int getTotalPrice(){//5
        return orderPrice*count;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }//6
}

/*

private LocalDateTime updateTime;

1 기존에 있던 위 두 변수를 삭제한다 그리고 BaseEntity를 상속받도록 소스코드를 수정합니다.
 */

/*
1+,2 주문할 상품과 주문 수량을 세팅합니다

3 현재 시간 기준으로 상품 가격을 주문 가격으로 세팅합니다. 상품 가격은 시간에 따라서 달라질 수 있다.

또한 쿠폰이나 할인을 적용하는 케이스들도 있지만 여기서는 고려하지 않는다.

4 주문수량 만큼 재고수량을 감소시킨다.

5 주문가격과 주문 수량을 곱해서 해당 상품을 주문한 총 가격을 계산하는 메소드입니다.

6 주문 취소 시 주문 수량만큼 상품의 재고를 더해줍니다.

 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님