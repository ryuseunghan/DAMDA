package com.mansa.damda.order;

import com.mansa.damda.market.Market;
import com.mansa.damda.market.MarketService;
import com.mansa.damda.product.Product;
import com.mansa.damda.product.ProductRepository;
import com.mansa.damda.store.Store;
import com.mansa.damda.store.StoreRepository;
import com.mansa.damda.user.User;
import com.mansa.damda.user.UserRepository;
import com.mansa.damda.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;
    private final MarketService marketService;

    @Transactional
    public void create(OrderDTO orderDTO){
        Order order = new Order();
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품 id 오류"));
        BigDecimal stock = product.getStockQuantity();
        BigDecimal ord = orderDTO.getOrderAmount();
        BigDecimal price = product.getPrice();
        if(stock.compareTo(ord) < 0){
            throw new IllegalArgumentException("수량이 부족합니다.");
        }
        order.setOrderAmount(orderDTO.getOrderAmount().stripTrailingZeros());

        product.setStockQuantity(stock.subtract(ord).stripTrailingZeros());

        order.setOrderPrice(ord.multiply(price).stripTrailingZeros());

        order.setProduct(product);

        order.setStore(product.getStore());

        order.setUser(userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("user id 오류")));
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
        productRepository.save(product);

        User user = userService.getUserById(orderDTO.getUserId());
        Market market = product.getStore().getMarket();
        List<Long> userStamps = user.getStamps();
        int flag = 0;
        for (int i = 0; i < userStamps.size(); i++) {
            if(userStamps.get(i).equals(market.getStamp().getStampId())){flag = 1;}
        }
        if(flag == 0){userService.issueStampToUser(orderDTO.getUserId(), market.getStamp().getStampId());}



    }

    public List<PurchaseHistoryDTO> getPurchaseHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자를 찾을 수 없습니다: " + userId));

        List<Order> userOrders = orderRepository.findByUser(user);

        List<PurchaseHistoryDTO> purchaseHistory = new ArrayList<>();
        for (int i = userOrders.size() - 1; i >= 0; i--) {
            Order order = userOrders.get(i);
            PurchaseHistoryDTO historyDTO = new PurchaseHistoryDTO();
            historyDTO.setOrderId(order.getOrderId());
            historyDTO.setOrderDate(order.getOrderDate());
            historyDTO.setOrderAmount(order.getOrderAmount());
            historyDTO.setOrderPrice(order.getOrderPrice());
            historyDTO.setProductName(order.getProduct().getProductName());
            purchaseHistory.add(historyDTO);
        }

        return purchaseHistory;
    }

    public List<PurchaseHistoryDTO> getSaleHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자를 찾을 수 없습니다: " + userId));
        Store store = storeRepository.findByUserUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 사용자의 가게를 찾을 수 없습니다" + userId));

        List<Order> storeOrders = orderRepository.findByStore(store);

        List<PurchaseHistoryDTO> purchaseHistory = new ArrayList<>();
        for (int i = storeOrders.size() - 1; i >= 0; i--) {
            Order order = storeOrders.get(i);
            PurchaseHistoryDTO historyDTO = new PurchaseHistoryDTO();
            historyDTO.setOrderId(order.getOrderId());
            historyDTO.setOrderDate(order.getOrderDate());
            historyDTO.setOrderAmount(order.getOrderAmount());
            historyDTO.setOrderPrice(order.getOrderPrice());
            historyDTO.setProductName(order.getProduct().getProductName());
            purchaseHistory.add(historyDTO);
        }

        return purchaseHistory;
    }


}
