package com.mansa.damda.finelocation;


import com.mansa.damda.largelocation.LargeLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fine-locations")
public class FineLocationController {
    @PostMapping("/select")
    public FineLocationDTO selectLargeLocation(@RequestBody FineLocationDTO fineLocationDTO) {
        return fineLocationDTO;
    }
}
