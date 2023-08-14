package com.mansa.damda.product;

import com.mansa.damda.category.CategoryRepository;
import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        product.setPrice(productRegisterDTO.getPrice().stripTrailingZeros());
        product.setStockQuantity(productRegisterDTO.getStockQuantity().stripTrailingZeros());
        product.setStore(storeRepository.findById(productRegisterDTO.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게 미입력 오류")));
        product.setMarket(marketRepository.findById(productRegisterDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("시장 미입력 오류")));
        product.setCategory(categoryRepository.findById(productRegisterDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 미입력 오류")));
        product.setCreateDate(LocalDateTime.now());
        productRepository.save(product);
    }
    public List<Product> searchProducts(String keyword) {
        Specification<Product> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")),
                        "%" + keyword + "%");

        return productRepository.findAll(spec);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    @Transactional
    public void updateProduct(Long productId, ProductUpdateDTO updateDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 조회 오류"));

        if(updateDTO.getProductName() != null){
            product.setProductName(updateDTO.getProductName());
        }
        if(updateDTO.getPrice() != null){
            product.setPrice(updateDTO.getPrice());
        }
        if(updateDTO.getStockQuantity() != null){
            product.setStockQuantity(updateDTO.getStockQuantity());
        }
        if(updateDTO.getCategoryId() != null){
            product.setCategory(categoryRepository.findById(updateDTO.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("카테고리 미입력 오류")));
        }
        productRepository.save(product);
    }


}
