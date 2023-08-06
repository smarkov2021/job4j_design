package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJSON {
    public static void main(String[] args) {
        final Agency agency = new Agency("First Agency", "8-800-555-35-35");
        JSONObject jsonAgency = new JSONObject(agency);
        System.out.println(jsonAgency.toString());
        List<String> list = new ArrayList<>();
        list.add("Refrigerator");
        list.add("Washing Machine");
        list.add("TV");
        JSONArray jsonItems = new JSONArray(list);

        final Flat flat = new Flat(true, "Radicheva 23 - 18", 35000,
                new String[] {"Refrigerator", "Washing Machine", "TV"}, agency);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("forLongRent", flat.isForLongRent());
        jsonObject.put("adress", flat.getAddress());
        jsonObject.put("cost", flat.getCost());
        jsonObject.put("items", jsonItems);
        jsonObject.put("agency", jsonAgency);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(flat).toString());
    }
}
