package com.mansa.damda.order;

import com.mansa.damda.product.Product;
import com.mansa.damda.user.User;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Long orderId) {
        this.orderId = orderId;
    }

}
