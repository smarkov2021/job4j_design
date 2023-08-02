package ru.job4j.serialization.json;

import java.util.Objects;

public class Agency {
    private final String name;
    private final String phone;

    public Agency(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Agency{"
                + "name='" + name + '\''
                + ", phone='" + phone + '\'' + '}';
    }
}
