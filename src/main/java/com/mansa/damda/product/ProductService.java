package com.mansa.damda.product;

import com.mansa.damda.category.CategoryRepository;
import com.mansa.damda.market.Market;
import com.mansa.damda.market.MarketRepository;
import com.mansa.damda.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        product.setProductDescription(productRegisterDTO.getProductDescription());
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
    public List<Product> searchProducts(String keyword, Long marketId) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")),
                    "%" + keyword.toLowerCase() + "%");

            if (marketId != null) {
                Join<Product, Market> marketJoin = root.join("market");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(marketJoin.get("marketId"), marketId));
            }

            return predicate;
        };

        return productRepository.findAll(spec);
    }
    public List<Product> getProductsByCategoryIdAndMarketId(Long categoryId, Long marketId) {
        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "productId"));
        Page<Product> productPage = productRepository.findByCategoryCategoryIdAndMarketMarketId(categoryId, marketId, pageable);
        return productPage.getContent();
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
        if(updateDTO.getProductDescription() != null){
            product.setProductDescription(updateDTO.getProductDescription());
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

    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("prdouctId 오류"));

        productRepository.delete(product);
    }

    //인기 상품을 stockQuantity가 낮은 순으로
    public List<Product> getProductsByStockQuantityLowestFirst(Long marketId) {
        Sort descendingSort = Sort.by(Sort.Direction.DESC, "stockQuantity");
        PageRequest pageRequest = PageRequest.of(0, 5, descendingSort);

        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("stockQuantity")));

            if (marketId != null) {
                Join<Product, Market> marketJoin = root.join("market");
                return criteriaBuilder.and(criteriaBuilder.equal(marketJoin.get("marketId"), marketId));
            }

            return null;
        };

        return productRepository.findAll(spec, pageRequest).getContent();
    }

    public ProductDTO getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return mapToProductDTO(product);
        }
        return null;
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setStoreName(product.getStore().getStoreName());
        // 다른 필드 설정
        return productDTO;
    }

    public List<Product> getProductsByStoreId(Long storeId) {
        return productRepository.findByStoreStoreId(storeId);
    }
}
