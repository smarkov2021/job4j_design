package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private  int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return in.pop();
    }

    public void push(T value) {
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        in.push(value);
        size++;
        for (int i = 0; i < size - 1; i++) {
            in.push(out.pop());
        }
    }
}