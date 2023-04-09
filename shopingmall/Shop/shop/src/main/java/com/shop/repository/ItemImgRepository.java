package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);//매개변수로 넘겨준 상품아이디를 가지며 상품 이미지 아이디의 오름차순으로 가져오는 쿼리메소드 입니다

    ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);//대표이미지르 찾는 쿼리 구매이력 페이지에서 주문상품의 대표이미지를 보여주기 위함

}

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님