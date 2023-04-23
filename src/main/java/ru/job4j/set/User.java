package ru.job4j.set;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
         return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar date = Calendar.getInstance();
        User firstUser = new User("Ivan", 14, date);
        System.out.println(firstUser);
        int hashCode1 = firstUser.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User secondUser = new User("Ivan", 14, date);
        System.out.println(secondUser);
        int hashCode2 = secondUser.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());
        for (User user : map.keySet()) {
            System.out.println("Users " + map.get(user).toString());
        }
        System.out.printf("User1 - хэшкод: %s, хэш %s, бакет: %s", hashCode1, hash1, bucket1);
        System.out.printf("User2 - хэшкод: %s, хэш %s, бакет: %s", hashCode2, hash2, bucket2);
        System.out.printf(String.valueOf(firstUser.equals(secondUser)));
    }
}
