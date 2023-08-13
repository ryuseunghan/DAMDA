package com.mansa.damda.product;

import com.mansa.damda.category.Category;
import com.mansa.damda.category.CategoryRepository;
import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;


    //상품 등록 api
    @Transactional
    public void create (ProductRegisterDTO productRegisterDTO){
        Product product = new Product();

        product.setProductName(productRegisterDTO.getProductName());
        product.setPrice(productRegisterDTO.getPrice());
        product.setStockQuantity(productRegisterDTO.getStockQuantity());
        product.setStore(storeRepository.findById(productRegisterDTO.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게 미입력 오류")));
        product.setMarket(marketRepository.findById(productRegisterDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("시장 미입력 오류")));
        product.setCategory(categoryRepository.findById(productRegisterDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 미입력 오류")));

        productRepository.save(product);
    }
}
