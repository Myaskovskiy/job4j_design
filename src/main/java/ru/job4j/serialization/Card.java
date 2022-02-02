package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")

public class Card  {

    @XmlAttribute
    private String model;
    @XmlAttribute
    private String color;

    public Card() { }

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
