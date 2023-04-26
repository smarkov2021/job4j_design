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
        int index = indexByKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
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

    private int indexByKey(K key) {
        int index = 0;
        if (key == null) {
            index = indexFor(hash(0));
        } else {
            index = indexFor(hash(key.hashCode()));
        }
        return index;
    }

    private void expand() {
        MapEntry<K, V>[] tableTiming = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> elem : tableTiming) {
            if (elem != null) {
                int index = indexByKey(elem.key);
                    table[index] = elem;
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
            if (Objects.hashCode(table[i].key) == Objects.hashCode(key)
                    && Objects.equals(table[i].key, key)) {
                rsl = table[i].value;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexByKey(key);
        if (table[index] != null && Objects.equals(table[index].key, key)) {
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
                    } else {
                        break;
                    }
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