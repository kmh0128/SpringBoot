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