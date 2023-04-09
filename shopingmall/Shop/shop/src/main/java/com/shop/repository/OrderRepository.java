package com.shop.repository;

import com.shop.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o " +
            "where o.member.email = :email " +
            "order by o.orderDate desc"
    )
    List<Order> findOrders(@Param("email") String email, Pageable pageable);//1

    @Query("select count(o) from Order o " +
            "where o.member.email = :email"
    )
    Long countOrder(@Param("email") String email);//2
}

/*
OrderRepository에 주문 이력을 조회하는 쿼리입니다 해당하는 문법은 JPQL입니다.

기억이 안날 경우 2장을 복습

1- 현재 로그인한 사용자의 주문 데이터를 페이징 조건에 맞춰서 조회합니다.

2- 현재 로그인한 회원의 주문 개수가 몇 개인지 조회
 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님