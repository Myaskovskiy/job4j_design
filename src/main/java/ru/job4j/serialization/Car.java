package ru.job4j.serialization;

import java.util.Arrays;

public class Car {
    private final boolean newCar;
    private final int cost;
    private final Card card;
    private final String[] offices;

    public Car(boolean newCar, int cost, Card card, String[] offices) {
        this.newCar = newCar;
        this.cost = cost;
        this.card = card;
        this.offices = offices;
    }

    @Override
    public String toString() {
        return "Car{"
                + "newCar=" + newCar
                + ", cost=" + cost
                + ", card=" + card
                + ", offices=" + Arrays.toString(offices)
                + '}';
    }
}
