package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;//1

    private ItemSellStatus searchSellStatus;//2

    private String searchBy;//3

    private String searchQuery = "";//4

}

/*
1-> 현재 시간과 상품 등록일을 비교해서 상품 데이터를 조회합니다.

조회 시간 기준은 아래와 같다.

all 상품등록일 전체

1d 최근 하루동안 등록된 상품

1w 최근 일주일 동안 등록된 상품

1m 최근 한달 동안 등록된 상품

6m 최근 6개월 동안 등록된 상품

2-> 상품의 판매상태를 기준으로 상품 데이터를 조회합니다.

3 -> 상품을 조회할 때 어떤 유형으로 조히할지 선택합니다.

itemNm -> 상품명

createdBy -> 상품 등록자 아이디

4 -> 조회할 검색어 저장할 변수입니다.

SearchBy가 itemNm일 경우 상품명 기준, createdBy일 경우 상품 등록자 아이디 기준으로 검색합니다.

QueryDsl을 Spring Data Jpa와 함께사용하기 위해서는 사용자 정의 리포지토리를 정의해야 한다.

총 3단계의 과정이 있다.

1  사용자 정의 인터페이스 구현

2  사용자 정의 인터페이스 작성

3 Spring Data Jpa 리포지토리에서 사용자 정의 인터페이스 상속

사용자 정의 인터페이스를 먼저 작성하겠습니다.

 */

//참고자료 백견불여일타 스프링부트와 JPA with 쇼핑몰 저자 변구훈님
