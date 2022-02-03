package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainCarJson {
    public static void main(String[] args) {
        JSONObject jsonCard = new JSONObject("{\"model\":\"porsche\",\"color\":\"Blue\"}");

        List<String> list = new ArrayList<>();
        list.add("SPb");
        list.add("Tver");
        JSONArray jsonOffices = new JSONArray(list);

        final Car car = new Car(true, 100500, new Card("BMW", "Green"),
                new String[] {"Moscow", "Tula"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newCar", car.isNewCar());
        jsonObject.put("cost", car.getCost());
        jsonObject.put("card", jsonCard);
        jsonObject.put("offices", jsonOffices);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }
}
