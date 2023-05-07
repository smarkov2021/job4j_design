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
        Set<User> copyOfPrevious = new HashSet<User>(previous);
        Set<User> copyOfCurrent = new HashSet<User>(current);
        Set<User> united = new HashSet<User>(current);
        Set<User> cross = new HashSet<User>(current);
        united.addAll(copyOfPrevious);
        cross.retainAll(copyOfPrevious);
        Set<Integer> arrayInt = new HashSet<Integer>();
        for (User user : united) {
            if (!arrayInt.contains(user.getId())) {
                arrayInt.add(user.getId());
            } else {
                changed++;
            }
        }
        copyOfPrevious.removeAll(cross);
        copyOfCurrent.removeAll(cross);
        deleted = copyOfPrevious.size() - changed;
        added = copyOfCurrent.size() - changed;

        return new Info(added, changed, deleted);
    }
}