package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainCar {
    public static void main(String[] args) {

        final Car car = new Car(true, 100500, new Card("BMW", "Green"),
                new String[] {"Moscow", "Tula"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));


        /* Модифицируем json-строку */
        final String carJson =
                "{"
                        + "\"newCar\":false,"
                        + "\"cost\":300000,"
                        + "\"card\":"
                        + "{"
                        + "\"model\":\"porsche\","
                        + "\"color\":\"blue\""
                        + "},"
                        + "\"offices\":"
                        + "[\"SP\",\"Orel\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
