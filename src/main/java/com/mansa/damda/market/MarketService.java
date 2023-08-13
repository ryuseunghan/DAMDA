package com.mansa.damda.market;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketService {
    public SelectMarketDTO selectFineLocation(SelectMarketDTO selectMarketDTO) {
        return selectMarketDTO;
    }
}
