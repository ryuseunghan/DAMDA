package com.mansa.damda.store;

import com.mansa.damda.product.ProductDTO;
import com.mansa.damda.product.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;
    @PostMapping("/register")
    public void registerStores(@RequestBody StoreRegisterDTO storeRegisterDTO){
        storeService.create(storeRegisterDTO);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Store>> getStoresByCategoryId(
            @RequestParam(required = false) Long categoryId,@RequestParam(required = false) Long marketId){
        List<Store> stores = storeService.getStoresByCategoryIdAndMarketId(categoryId, marketId);
        return ResponseEntity.ok(stores);
    }

    @PatchMapping("/update/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @RequestBody StoreUpdateDTO updateDTO) {
        storeService.updateStore(storeId, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long storeId) {
        Store store = storeService.getStoreById(storeId);
        return ResponseEntity.ok(store);
    }


}
