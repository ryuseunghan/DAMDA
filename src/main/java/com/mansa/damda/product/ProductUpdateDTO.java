package com.mansa.damda.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    private String productName;

    private BigDecimal price;

    private BigDecimal stockQuantity;

    private Long categoryId;
}
