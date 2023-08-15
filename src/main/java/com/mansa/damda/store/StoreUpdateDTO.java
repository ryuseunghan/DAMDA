package com.mansa.damda.store;

import lombok.Data;

@Data
public class StoreUpdateDTO {
    private String storeName;

    private Long userId;

    private Long marketId;

    private Long categoryId;

    private String storeDescription;

    private String accountName;

    private Integer accountDigit;

    private String accountBank;
}
