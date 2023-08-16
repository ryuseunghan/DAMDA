package com.mansa.damda.product;

import com.mansa.damda.store.Store;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long productId;

    private String productName;

    private BigDecimal price;

    private String storeName;

    private String productDescription;
}
