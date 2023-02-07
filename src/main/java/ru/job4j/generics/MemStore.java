package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.get(id) != null) {
                storage.replace(id, model);
                return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (storage.get(id) != null) {
            storage.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
