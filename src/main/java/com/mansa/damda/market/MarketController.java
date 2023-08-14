package com.mansa.damda.market;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
public class MarketController {
    @GetMapping("/select")
    public SelectMarketDTO selectMarket(@RequestBody SelectMarketDTO selectMarketDTO){
        return selectMarketDTO;
    }
}
