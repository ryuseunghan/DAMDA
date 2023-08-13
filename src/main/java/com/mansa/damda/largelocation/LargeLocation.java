package com.mansa.damda.largelocation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "large_locations")
@Getter
@Setter
public class LargeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "large_location_id")
    private Long largeLocationId;

    @Column(name = "large_location_name")
    private String largeLocationName;
}
