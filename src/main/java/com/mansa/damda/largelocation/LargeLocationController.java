package com.mansa.damda.largelocation;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/large-locations")
@Api(tags = "large-locations API")
public class LargeLocationController {
    @PostMapping("/select")
    public LargeLocationDTO selectLargeLocation(@RequestBody LargeLocationDTO largeLocationDTO) {
        return largeLocationDTO;
    }
}
