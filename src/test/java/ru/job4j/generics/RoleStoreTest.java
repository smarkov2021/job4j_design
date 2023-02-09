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
    @Test
    void whenAddDuplicateAndFindUsernameIsSergey() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        store.add(new Role("1", "Maxim", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Sergey");
    }
    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        store.replace("1", new Role("1", "Maxim", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        store.replace("10", new Role("1", "Maxim", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Sergey");
    }
    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        boolean rsl = store.replace("1", new Role("1", "Maxim", "Engineer"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        boolean rsl = store.replace("2", new Role("2", "Maxim", "Engineer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", "Analytic"));
        boolean rsl = store.delete("342");
        assertThat(rsl).isFalse();
    }пр
}