package com.mansa.damda.market;

import com.mansa.damda.finelocation.FineLocation;
import com.mansa.damda.stamp.Stamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "markets")
public class Market{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long marketId;

    @Column(name = "market_name")
    private String marketName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fine_location_id")
    private FineLocation fineLocation;

    @OneToOne
    @JoinColumn(name= "stamp_id")
    private Stamp stamp;
}
