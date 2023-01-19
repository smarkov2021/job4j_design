package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }
    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> newList  = simpleConvert.toList("first", "second", "three", "four", "five", "second");
        assertThat(newList).hasSize(6)
                .contains("four")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("zero", "second", "test")
                .doesNotContain("three", Index.atIndex(1));
    }
    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> newList  = simpleConvert.toSet(
                "first", "second", "three", "four", "five", "second", "test");
        assertThat(newList).hasSize(6)
                .contains("three")
                .contains("second")
                .containsAnyOf("first", "second", "test")
                .doesNotContain("secondElem");
    }
    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> newMap  = simpleConvert.toMap(
                "first", "second", "three", "four", "five", "second", "test");
        assertThat(newMap).hasSize(6)
                .containsKeys("first", "three")
                .containsValues(1, 4, 2)
                .containsEntry("three", 2);
    }
}