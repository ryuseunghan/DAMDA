package com.mansa.damda.largelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LargeLocationRepository extends JpaRepository<LargeLocation, Long> {
}
