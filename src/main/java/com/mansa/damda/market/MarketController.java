package com.mansa.damda.market;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
@Api(tags = "markets API")
public class MarketController {
    @GetMapping("/select")
    public SelectMarketDTO selectMarket(@RequestBody SelectMarketDTO selectMarketDTO){
        return selectMarketDTO;
    }
}
