package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> mapOfElem = new HashMap<>();
        for (User user : previous) {
            mapOfElem.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (mapOfElem.containsKey(user.getId()) && !mapOfElem.get(user.getId()).equals(user.getName())) {
                changed++;
            }
            mapOfElem.put(user.getId(), user.getName());
        }
        added = mapOfElem.size() - previous.size();
        deleted = mapOfElem.size() - current.size();
        return new Info(added, changed, deleted);
    }
}