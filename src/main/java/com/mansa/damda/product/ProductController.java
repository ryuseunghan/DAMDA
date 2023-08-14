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
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String keyword){
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
    //카테고리 조회
    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam(required = false) Long categoryId){
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateDTO updateDTO) {
        productService.updateProduct(productId, updateDTO);
        return ResponseEntity.noContent().build();
    }


}
