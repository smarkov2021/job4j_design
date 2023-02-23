package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> l = head;
        final Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> param = head;
        for (int i = 0; i < index; i++) {
            param = param.next;
        }
        return param.getItem();
    }

    public void addFirst(T value) {
        if (head != null) {
            Node oldHead = new Node(head.item, head.next);
            head.item = value;
            head.next = oldHead;
        } else {
            head = new Node(value, null);
        }
    }

    public T deleteFirst() {
        T oldValue;
        if (head != null) {
            Node newHead = head.next;
            oldValue = head.item;
            head.next = null;
            head.item = null;
            head = newHead;
        } else {
            throw new NoSuchElementException();
        }
        return oldValue;
}

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            Node<T> param = head;
            Node<T> prev;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return param != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                prev = param;
                param = param.next;
                return prev.getItem();
            }
        };
    }

    private static class Node<T> {
        private T item;

        public T getItem() {
            return item;
        }

        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}