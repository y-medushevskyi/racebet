package com.example.racebet;

public class Bet {
    private String car;
    private double amount;

    public Bet(String car, double amount) {
        this.car = car;
        this.amount = amount;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}