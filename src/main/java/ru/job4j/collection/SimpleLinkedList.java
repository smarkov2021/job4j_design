package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> first;

    void linkLast(E e) {
        final Node<E> l = head;
        final Node<E> newNode = new Node<>(e, null);
        this.head = newNode;
        if (l != null) {
            l.next = newNode;
        } else {
            first = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void add(E value) {
        linkLast(value);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> param = first;
        int i = 0;
        while (i < index) {
            param = param.next;
            i++;
        }
        return param.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            Node<E> param = first;
            Node<E> prev;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return param != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                prev = param;
                param = param.next;
                return prev.getItem();
            }
        };
    }

    private static class Node<E> {
        private E item;

        public E getItem() {
            return item;
        }

        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}