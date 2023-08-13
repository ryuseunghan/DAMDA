package com.mansa.damda.largelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LargeLocationRepository extends JpaRepository<LargeLocation, Long> {
    Optional<LargeLocation> findByLargeLocationId(Long largeLocationId);
}
