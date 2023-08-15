package com.mansa.damda.order;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Api(tags = "orders API")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/register")
    public void register(@RequestBody OrderDTO orderDTO){orderService.create(orderDTO);}
    @GetMapping("/{userId}/purchase-history")
    public ResponseEntity<List<PurchaseHistoryDTO>> getPurchaseHistory(@PathVariable Long userId) {
        List<PurchaseHistoryDTO> purchaseHistory = orderService.getPurchaseHistory(userId);
        return ResponseEntity.ok(purchaseHistory);
    }

    @GetMapping("/{userId}/sale-history")
    public ResponseEntity<List<PurchaseHistoryDTO>> getSaleHistory(@PathVariable("userId") Long userId) {
        List<PurchaseHistoryDTO> purchaseHistory = orderService.getSaleHistory(userId);
        return ResponseEntity.ok(purchaseHistory);
    }
}
