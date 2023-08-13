package com.mansa.damda.finelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FineLocationRepository extends JpaRepository<FineLocation, Long> {
}
