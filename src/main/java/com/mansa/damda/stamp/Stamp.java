package com.mansa.damda.stamp;

import com.mansa.damda.market.Market;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Stamps")
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_id")
    private Long stampId;

    @OneToOne
    @JoinColumn(name = "market_id")
    private Market market;
}
