package ru.job4j.serialization;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String model;
    private final String color;

    public Card(String model, String color) {
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" + "model='" + model
                + '\'' + ", color='"
                + color + '\'' + '}';
    }
}
