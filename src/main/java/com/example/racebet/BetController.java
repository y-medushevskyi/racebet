package com.example.racebet;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/place")
    public String placeBet(@RequestParam String car, @RequestParam double amount) {
        return betService.placeBet(car, amount);
    }

    @GetMapping("/info")
    public Map<String, Double> getBets(@RequestParam(required = false) String car) {
        return betService.getBets(car);
    }
}
