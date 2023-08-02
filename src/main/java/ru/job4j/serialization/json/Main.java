package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Agency agency = new Agency("First Agency", "8-800-555-35-35");
        final Flat attractiveFlat = new Flat(true, "Radicheva 23 - 18", 35000,
                new String[] {"Refrigerator", "Washing Machine", "TV"}, agency);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(attractiveFlat));

        final String flatJson =
                "{"
                        + "\"forLongRent\":true,"
                        + "\"address\":\"Radicheva 23 - 18\","
                        + "\"cost\":35000,"
                        + "\"items\":"
                        + "[\"Refrigerator\",\"Washing Machine\",\"TV\"],"
                        + "\"agency\":"
                        + "{"
                        + "\"name\":\"First Agency\","
                        + "\"phone\":\"8-800-555-35-35\""
                        + "}"
                        + "}";
        final Flat flatMod = gson.fromJson(flatJson, Flat.class);
        System.out.println(flatMod);
    }
}