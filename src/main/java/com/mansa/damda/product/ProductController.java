package com.mansa.damda.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/register")
    public void register(@RequestBody ProductRegisterDTO productRegisterDTO){productService.create(productRegisterDTO);}

    //키워드 검색
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long marketId) {

        List<Product> products = productService.searchProducts(keyword, marketId);
        return ResponseEntity.ok(products);
    }
    //카테고리 조회
    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategoryIdAndMarketId(
            @RequestParam Long categoryId, @RequestParam Long marketId) {
        List<Product> products = productService.getProductsByCategoryIdAndMarketId(categoryId, marketId);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateDTO updateDTO) {
        productService.updateProduct(productId, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lowest-stock")
    public ResponseEntity<List<Product>> getProductsByStockQuantityLowestFirst(
            @RequestParam(required = false) Long marketId) {
        List<Product> products = productService.getProductsByStockQuantityLowestFirst(marketId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-store")
    public ResponseEntity<List<Product>> getProductsByStoreId(@RequestParam Long storeId) {
        List<Product> products = productService.getProductsByStoreId(storeId);
        return ResponseEntity.ok(products);
    }

}
