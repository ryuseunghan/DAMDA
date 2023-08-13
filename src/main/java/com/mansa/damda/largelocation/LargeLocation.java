package com.mansa.damda.largelocation;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "large_locations")
@Getter
public class LargeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "large_location_id")
    private Long largeLocationId;

    @Column(name = "large_location_name")
    private String largeLocationName;
}
