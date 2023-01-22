package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }
    @Override
    public boolean hasNext() {
        if (row == data.length) {
            return false;
        }
        while (data[row] == null || data[row].length == 0) {
                column = 0;
                row++;
                if (row == data.length) {
                    return false;
                }
        }
        return  true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[row][column];
        column++;
        if (data[row].length == column) {
            row++;
            column = 0;
        }
        return result;
    }
}