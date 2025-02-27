package com.example.racebet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RaceBetApplicationTests {

    private BetService betService;

    @BeforeEach
    public void setUp() {
        betService = new BetService();
    }

    @Test
    public void testPlaceBet_SuccessMessage() {
        String result = betService.placeBet("Ferrari", 100);
        assertEquals("Bet placed successfully.", result);
    }

    @Test
    public void testPlaceBet_CorrectAmountStored() {
        betService.placeBet("Ferrari", 100);
        Map<String, Double> bets = betService.getBets("ferrari");
        assertEquals(100.0, bets.get("ferrari"));
    }

    @Test
    public void testPlaceBet_InvalidCar() {
        String result = betService.placeBet("Porsche", 100);
        assertEquals("Invalid car brand. Allowed: Ferrari, BMW, Audi, Honda.", result);
    }

    @Test
    public void testPlaceBet_InvalidCar_ReturnsError() {
        Map<String, Double> bets = betService.getBets("porsche");
        assertTrue(bets.containsKey("error: invalid car brand"));
        assertEquals(0.0, bets.get("error: invalid car brand"));
    }

    @Test
    public void testPlaceBet_InvalidAmount() {
        String result = betService.placeBet("BMW", -10);
        assertEquals("Amount must be greater than 0.", result);
    }


    @Test
    public void testGetBets_SpecificCar() {
        betService.placeBet("Audi", 50);
        Map<String, Double> bets = betService.getBets("audi");
        assertEquals(50.0, bets.get("audi"));
    }

    @Test
    public void testGetBets_EmptyInput() {
        Map<String, Double> bets = betService.getBets("");
        assertTrue(bets.isEmpty());
    }

    @Test
    public void testGetBets_InvalidCar() {
        Map<String, Double> bets = betService.getBets("mazda");
        assertTrue(bets.containsKey("error: invalid car brand"));
        assertEquals(0.0, bets.get("error: invalid car brand"));
    }

    @Test
    public void testGetBets_AllBets() {
        betService.placeBet("Ferrari", 100);
        betService.placeBet("BMW", 200);
        betService.placeBet("Audi", 300);

        Map<String, Double> bets = betService.getBets("");
        assertEquals(100.0, bets.get("ferrari"));
        assertEquals(200.0, bets.get("bmw"));
        assertEquals(300.0, bets.get("audi"));
    }
}
