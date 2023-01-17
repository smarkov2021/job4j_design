package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }
    @Test
    void isThisUNKNOWN() {
        Box box = new Box(7, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }
    @Test
    void isSpheresVertex() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0);
    }
    @Test
    void isUNKNOWNVertex() {
        Box box = new Box(0, -610);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(0);
    }
    @Test
    void isExisting() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }
    @Test
    void isNotExisting() {
        Box box = new Box(0, -610);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }
    @Test
    void getAreaIfSphereIsEqual1256() {
        Box box = new Box(0, 1);
        double square = box.getArea();
        assertThat(square).isEqualTo(12.56d, withPrecision(0.01d));
    }
    @Test
    void getAreaIfCubeIsEqual6() {
        Box box = new Box(8, 1);
        double square = box.getArea();
        double delta = 0.01;
        assertThat(square).isEqualTo(6.0d, withPrecision(0.01d));
    }
}