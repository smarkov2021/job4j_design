package ru.job4j.serialization.json;

import java.util.Arrays;

public class Flat {
    private final boolean forLongRent;
    private final String address;
    private final int cost;
    private final String[] items;
    private final Agency agency;

    public Flat(boolean forLongRent, String address, int cost, String[] items, Agency agency) {
        this.forLongRent = forLongRent;
        this.address = address;
        this.cost = cost;
        this.items = items;
        this.agency = agency;
    }

    public boolean isForLongRent() {
        return forLongRent;
    }

    public String getAddress() {
        return address;
    }

    public String[] getItems() {
        return items;
    }

    public Agency getAgency() {
        return agency;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Flat{" + "forLongRent=" + forLongRent
                + ", address='" + address + '\''
                + ", cost=" + cost
                + ", items=" + Arrays.toString(items)
                + ", agency=" + agency + '}';
    }
}
