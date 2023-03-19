package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> remElem;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        remElem = new ArrayList<>(Arrays.asList(1, 2, 19));
        }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> predicate = elem -> elem < 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> predicate = elem -> elem < 2;
        ListUtils.replaceIf(input, predicate, 7);
        assertThat(input).hasSize(2).containsSequence(7, 3);
    }

    @Test
    void whenRemove3() {
        ListUtils.removeAll(input, remElem);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}