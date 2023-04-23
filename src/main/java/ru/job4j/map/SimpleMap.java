package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        MapEntry newElem = new MapEntry<>(key, value);
        int index = 0;
        index = key == null ? indexFor(hash(0)) : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = newElem;
            modCount++;
            count++;
            rsl = true;
        }
        if (count >= capacity * LOAD_FACTOR) {
            count = 0;
            expand();
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] tableTiming = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> elem : tableTiming) {
            if (elem != null) {
            put(elem.key, elem.value);
            }
        }

    }

    @Override
    public V get(K key) {
        V rsl = null;
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                continue;
            }
            if (key == null) {
                if (table[i].key == null) {
                   rsl = table[i].value;
                   break;
                }
            } else if (table[i].key != null && table[i].key.hashCode() == key.hashCode() && table[i].key.equals(key)) {
                rsl = table[i].value;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = 0;
        if (key == null) {
            index = indexFor(hash(0));
        } else {
            index = indexFor(hash(key.hashCode()));
        }
        if (table[index] != null
                && ((table[index].key != null && table[index].key.equals(key))
                || (table[index].key == null && key == null))) {
            table[index] = null;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity) {
                    if (table[index] == null) {
                        index++;
                        continue;
                    }
                    break;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}