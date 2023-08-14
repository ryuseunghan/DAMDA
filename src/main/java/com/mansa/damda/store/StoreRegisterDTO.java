package com.mansa.damda.store;

import com.mansa.damda.category.Category;
import com.mansa.damda.market.Market;
import com.mansa.damda.user.User;
import lombok.Data;

@Data
public class StoreRegisterDTO {

    private String storeName;

    private Long userId;

    private Long marketId;

    private Long categoryId;

    private String storeDescription;

    private String accountName;

    private Integer accountDigit;

    private String accountBank;
}
