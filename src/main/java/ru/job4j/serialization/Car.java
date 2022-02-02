package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean newCar;
    @XmlAttribute
    private int cost;

    private Card card;
    @XmlElementWrapper(name = "offices")
    @XmlElement(name = "office")
    private String[] offices;

    public Car() { }

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
