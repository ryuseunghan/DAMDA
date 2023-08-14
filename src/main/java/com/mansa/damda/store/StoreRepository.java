package com.mansa.damda.store;


import com.mansa.damda.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long storeId);
    List<Store> findByCategoryCategoryId(Long categoryId);
    boolean existsByStoreName(String storeName);
}
