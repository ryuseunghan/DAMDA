package com.mansa.damda.order;

import com.mansa.damda.product.Product;
import com.mansa.damda.product.ProductRepository;
import com.mansa.damda.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(OrderDTO orderDTO){
        Order order = new Order();
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품 id 오류"));
        BigDecimal stock = product.getStockQuantity();
        BigDecimal ord = orderDTO.getOrderAmount();
        if(stock.compareTo(ord) < 0){
            throw new IllegalArgumentException("수량이 부족합니다.");
        }
        order.setOrderAmount(orderDTO.getOrderAmount().stripTrailingZeros());

        product.setStockQuantity(stock.subtract(ord).stripTrailingZeros());

        order.setOrderPrice(ord.multiply(order.getOrderPrice()).stripTrailingZeros());

        order.setProduct(productRepository.findById(orderDTO.getProductId())
                .orElseThrow(()->new IllegalArgumentException("상품 id 오류")));

        order.setUser(userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("user id 오류")));
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
        productRepository.save(product);
    }

}
