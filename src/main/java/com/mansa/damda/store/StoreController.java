package com.mansa.damda.store;

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
    public ResponseEntity<List<Store>> getStoresByCategoryId(@RequestParam(required = false) Long categoryId){
        List<Store> stores = storeService.getStoresByCategoryId(categoryId);
        return ResponseEntity.ok(stores);
    }

}
