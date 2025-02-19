package com.example.racebet;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BetService {

    private final List<Bet> bets = new CopyOnWriteArrayList<>();

    private static final List<String> ALLOWED_CARS = List.of("Ferrari", "BMW", "Audi", "Honda");

    public String placeBet(String car, double amount) {
        if (!ALLOWED_CARS.contains(car)) {
            return "Invalid car brand. Allowed brands are: Ferrari, BMW, Audi, Honda.";
        }

        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }

        bets.add(new Bet(car, amount));
        return "Bet placed successfully.";
    }

    public String getBets(String car) {
        if (car != null) {
            if (!ALLOWED_CARS.contains(car)) {
                return "Invalid car brand. Allowed brands are: Ferrari, BMW, Audi, Honda.";
            }

            double totalAmount = bets.stream()
                    .filter(bet -> bet.getCar().equals(car))
                    .mapToDouble(Bet::getAmount)
                    .sum();

            if (totalAmount == 0) {
                return "No bets placed on " + car + ".";
            }
            return "Total bets on " + car + ": $" + totalAmount;
        }

        if (bets.isEmpty()) {
            return "No bets placed yet.";
        }

        StringBuilder result = new StringBuilder();
        ConcurrentHashMap<String, Double> totalBets = new ConcurrentHashMap<>();

        bets.forEach(bet -> totalBets.merge(bet.getCar(), bet.getAmount(), Double::sum));

        totalBets.forEach((k, v) -> result.append(k).append(": $").append(v).append("\n"));
        return result.toString();
    }
}