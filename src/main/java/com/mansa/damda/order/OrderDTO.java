package com.mansa.damda.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private BigDecimal orderAmount;

    private Long productId;

    private Long userId;
}
