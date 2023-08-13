package com.mansa.damda.finelocation;

import com.mansa.damda.largelocation.LargeLocation;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "fine_locations")
@Getter
public class FineLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_location_id")
    private Long fineLocationId;

    @Column(name = "fine_location_name")
    private String fineLocationName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "large_location_id")
    private LargeLocation largeLocation;
}
