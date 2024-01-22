package mingu.spring.bucket4j.controller;

import lombok.RequiredArgsConstructor;
import mingu.spring.bucket4j.service.RateLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RateLimitController {

    private final RateLimitService rateLimitService;

    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
        long count = rateLimitService.getAvailableTokens(id);
        return String.format("Available count = %d", count);
    }
}
