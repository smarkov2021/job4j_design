package ru.job4j.generics;

public class Role extends Base {

    private final String username;

    private final String role;

    public Role(String id, String name, String role) {
        super(id);
        this.username = name;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
