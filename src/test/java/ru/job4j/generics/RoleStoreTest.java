package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsSergey() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Sergey");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

}