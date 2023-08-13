package com.mansa.damda.largelocation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LargeLocationService {
    public LargeLocationDTO selectLargeLocation(LargeLocationDTO largeLocationDTO) {
        return largeLocationDTO;
    }
}
