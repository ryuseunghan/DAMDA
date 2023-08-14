package com.mansa.damda.store;

import com.mansa.damda.category.CategoryRepository;
import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.user.User;
import com.mansa.damda.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;

    public void create(StoreRegisterDTO storeRegisterDTO){
        Store store = new Store();
        if(storeRepository.existsByStoreName(storeRegisterDTO.getStoreName())){
            throw new IllegalArgumentException("이미 사용 중인 가게명입니다.");
        }
        store.setStoreName(storeRegisterDTO.getStoreName());
        store.setStoreDescription(storeRegisterDTO.getStoreDescription());
        User user = userRepository.findById(storeRegisterDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 id 오류"));
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
    public List<Store> getStoresByCategoryId(Long categoryId){
        return storeRepository.findByCategoryCategoryId(categoryId);
    }
}