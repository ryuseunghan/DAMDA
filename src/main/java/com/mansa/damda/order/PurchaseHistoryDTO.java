package com.mansa.damda.order;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseHistoryDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal orderAmount;
    private BigDecimal orderPrice;
    private String productName;
}
