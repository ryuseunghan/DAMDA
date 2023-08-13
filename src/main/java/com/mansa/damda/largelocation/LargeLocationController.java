package com.mansa.damda.largelocation;

import com.mansa.damda.user.User;
import com.mansa.damda.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/large-locations")
public class LargeLocationController {
    @PostMapping("/select")
    public LargeLocationDTO selectLargeLocation(@RequestBody LargeLocationDTO largeLocationDTO) {
        return largeLocationDTO;
    }
}
