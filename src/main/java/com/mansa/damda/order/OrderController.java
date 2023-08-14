package com.mansa.damda.order;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Api(tags = "orders API")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/register")
    public void register(@RequestBody OrderDTO orderDTO){orderService.create(orderDTO);}
}
