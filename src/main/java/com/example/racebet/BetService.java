package com.example.racebet;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BetService {

    private final Map<String, Double> bets = new ConcurrentHashMap<>();
    private static final Set<String> ALLOWED_CARS = Set.of("ferrari", "bmw", "audi", "honda");


    public String placeBet(String car, double amount) {
        car = car.toLowerCase();
        if (!isValidCar(car)) {
            return "Invalid car brand. Allowed: Ferrari, BMW, Audi, Honda.";
        }

        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }

        bets.merge(car, amount, Double::sum);
        return "Bet placed successfully.";
    }

    public Map<String, Double> getBets(String car) {
        if (car == null || car.isBlank()) {
            return bets.isEmpty() ? Map.of() : Map.copyOf(bets);
        }

        car = car.toLowerCase();
        if (!isValidCar(car)) {
            return Map.of("error: invalid car brand", 0.0);
        }

        return Map.of(car, bets.getOrDefault(car, 0.0));
    }

    private boolean isValidCar(String car) {
        return ALLOWED_CARS.contains(car.toLowerCase());
    }
}
