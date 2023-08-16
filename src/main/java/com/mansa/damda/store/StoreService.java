package com.mansa.damda.store;

import com.mansa.damda.category.CategoryRepository;
import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.product.Product;
import com.mansa.damda.product.ProductDTO;
import com.mansa.damda.user.User;
import com.mansa.damda.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;

    public void create(StoreRegisterDTO storeRegisterDTO) {
        Store store = new Store();
        if (storeRepository.existsByStoreName(storeRegisterDTO.getStoreName())) {
            throw new IllegalArgumentException("이미 사용 중인 가게명입니다.");
        }

        store.setStoreName(storeRegisterDTO.getStoreName());
        store.setStoreDescription(storeRegisterDTO.getStoreDescription());
        User user = userRepository.findById(storeRegisterDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 id 오류"));
        if (user.getAccountName() != null) {
            throw new IllegalArgumentException("이미 등록 된 가게가 있습니다");
        }
        user.setAccountBank(storeRegisterDTO.getAccountBank());
        user.setAccountDigit(storeRegisterDTO.getAccountDigit());
        user.setAccountName(storeRegisterDTO.getAccountName());
        store.setUser(user);
        store.setMarket(marketRepository.findById(storeRegisterDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("시장 id 오류")));
        store.setCategory(categoryRepository.findById(storeRegisterDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("가게 id 오류")));

        storeRepository.save(store);
        userRepository.save(user);
    }

    public List<Store> getStoresByCategoryIdAndMarketId(Long categoryId, Long marketId) {
        return storeRepository.findByCategoryCategoryIdAndMarketMarketId(categoryId, marketId);
    }

    @Transactional
    public void updateStore(Long storeId, StoreUpdateDTO storeUpdateDTO) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("상품 조회 오류"));
        if (storeUpdateDTO.getStoreName() != null) {
            store.setStoreName(storeUpdateDTO.getStoreName());
        }
        if (storeUpdateDTO.getStoreDescription() != null) {
            store.setStoreDescription(storeUpdateDTO.getStoreDescription());
        }
        User user = userRepository.findById(storeUpdateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 id 오류"));
        if (storeUpdateDTO.getAccountBank() != null) {
            user.setAccountBank(storeUpdateDTO.getAccountBank());
        }
        if (storeUpdateDTO.getAccountName() != null) {
            user.setAccountName(storeUpdateDTO.getAccountName());
        }
        if (storeUpdateDTO.getAccountDigit() != null) {
            user.setAccountDigit(storeUpdateDTO.getAccountDigit());
        }

        if (storeUpdateDTO.getMarketId() != null) {
            store.setMarket(marketRepository.findById(storeUpdateDTO.getMarketId())
                    .orElseThrow(() -> new IllegalArgumentException("시장 id 오류")));
        }
        store.setUser(user);
        if (storeUpdateDTO.getCategoryId() != null) {
            store.setCategory(categoryRepository.findById(storeUpdateDTO.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("가게 id 오류")));
        }
        storeRepository.save(store);
        userRepository.save(user);
    }
    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 id 오류"));
    }

}