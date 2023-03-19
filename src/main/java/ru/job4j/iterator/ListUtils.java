package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        List<T> deletedElem = list.stream().filter(filter).collect(Collectors.toList());
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (deletedElem.contains(value)) {
                iterator.remove();
            }
            iterator.next();
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        List<T> changedElem = list.stream().filter(filter).collect(Collectors.toList());
        while (iterator.hasNext()) {
            T currentValue = iterator.next();
            if (changedElem.contains(currentValue)) {
                iterator.set(value);
            }
            iterator.next();
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T currentValue = iterator.next();
            if (elements.contains(currentValue)) {
                iterator.remove();
            }
            iterator.next();
        }
    }

}