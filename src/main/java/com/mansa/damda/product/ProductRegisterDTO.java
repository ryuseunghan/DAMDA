package com.mansa.damda.product;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRegisterDTO {
    private String productName;

    private BigDecimal price;

    private String productDescription;

    private BigDecimal stockQuantity;

    private Long storeId;

    private Long marketId;

    private Long categoryId;
}
