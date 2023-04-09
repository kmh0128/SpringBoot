package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);//1

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);//2
}


//2클래스에 메인페이지에 보여줄 상품 리스트를 가져오는 메소드를 생성
//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님