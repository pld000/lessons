package ordered_list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedListTest {
    public OrderedList<Integer> listAsc;
    public OrderedList<Integer> listDesc;

    @BeforeEach
    void setUp() {
        listAsc = new OrderedList<>(true);
        listDesc = new OrderedList<>(false);
    }

    @AfterEach
    void tearDown() {
        listAsc = null;
        listDesc = null;
    }

    @Test
    void compare() {
        Assertions.assertEquals(-1, listAsc.compare(5, 10), "Failed compare less");
        Assertions.assertEquals(0, listAsc.compare(0, 0), "Failed compare zeros");
        Assertions.assertEquals(1, listAsc.compare(34, 10), "Failed compare greater");
        Assertions.assertEquals(0, listAsc.compare(99, 99), "Failed compare equals");
    }

    @Test
    void addOneElementAsc() {
        int someValue = 99;
        listAsc.add(someValue);
        Assertions.assertEquals(1, listAsc.count(), "Failed addOneElementAsc");
        Assertions.assertEquals(someValue, listAsc.head.value, "Failed addOneElementAsc head value");
        Assertions.assertEquals(someValue, listAsc.tail.value, "Failed addOneElementAsc tail value");
    }

    @Test
    void addOneElementDesc() {
        int someValue = 99;
        listDesc.add(someValue);
        Assertions.assertEquals(1, listDesc.count(), "Failed addOneElementDesc");
        Assertions.assertEquals(someValue, listDesc.head.value, "Failed addOneElementDesc head value");
        Assertions.assertEquals(someValue, listDesc.tail.value, "Failed addOneElementDesc tail value");
    }

    @Test
    void addManyElementsAsc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add(i);
        }

        Assertions.assertEquals(size, listAsc.count(), "Failed addManyElementsAsc size");
        Assertions.assertEquals(0, listAsc.head.value, "Failed addManyElementsAsc head value");
        Assertions.assertEquals(99, listAsc.tail.value, "Failed addManyElementsAsc tail value");
    }

    @Test
    void addManyElementsDesc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listDesc.add(i);
        }

        Assertions.assertEquals(size, listDesc.count(), "Failed addManyElementsDesc size");
        Assertions.assertEquals(99, listDesc.head.value, "Failed addManyElementsDesc head value");
        Assertions.assertEquals(0, listDesc.tail.value, "Failed addManyElementsDesc tail value");
    }

    @Test
    void addElementsAsc() {
        Integer[] testValues = {23, 54, 0, 4, 26, 11, 5, 999, -8, 32, 56, 26, 1, 47, 81, -37};
        for (int i = 0; i < testValues.length; i++) {
            listAsc.add(testValues[i]);
        }

        Assertions.assertEquals(testValues.length, listAsc.count(), "Failed addElementsAsc size");
        Assertions.assertEquals(-37, listAsc.head.value, "Failed addElementsAsc head value");
        Assertions.assertEquals(999, listAsc.tail.value, "Failed addElementsAsc tail value");
    }

    @Test
    void addElementsDesc() {
        Integer[] testValues = {23, 54, 0, 4, 26, 11, 5, 999, -8, 32, 56, 26, 1, 47, 81, -37};
        for (int i = 0; i < testValues.length; i++) {
            listDesc.add(testValues[i]);
        }

        Assertions.assertEquals(testValues.length, listDesc.count(), "Failed addElementsDesc size");
        Assertions.assertEquals(999, listDesc.head.value, "Failed addElementsDesc head value");
        Assertions.assertEquals(-37, listDesc.tail.value, "Failed addElementsDesc tail value");
    }

    @Test
    void findInEmptyListAsc() {
        var node = listAsc.find(999);
        Assertions.assertNull(node, "Failed findInEmptyListAsc");
    }

    @Test
    void findInEmptyListDesc() {
        var node = listDesc.find(999);
        Assertions.assertNull(node, "Failed findInEmptyListDesc");
    }

    @Test
    void findAnyElementAsc() {
        listAsc.add(44);
        listAsc.add(99);
        listAsc.add(5);
        listAsc.add(23);
        listAsc.add(60);

        var node = listAsc.find(60);
        Assertions.assertEquals(60, node.value, "Failed findAnyElementAsc");

        node = listAsc.find(999);
        Assertions.assertNull(node, "Failed findAnyElementAsc not exist");
    }

    @Test
    void findAnyElementDesc() {
        listDesc.add(44);
        listDesc.add(99);
        listDesc.add(5);
        listDesc.add(23);
        listDesc.add(60);

        var node = listDesc.find(23);
        Assertions.assertEquals(23, node.value, "Failed findAnyElementDesc");

        node = listDesc.find(999);
        Assertions.assertNull(node, "Failed findAnyElementDesc not exist");
    }

    @Test
    void deleteFromEmptyListAsc() {
        listAsc.delete(999);
        Assertions.assertEquals(0, listDesc.count(), "Failed deleteFromEmptyListAsc size");
    }

    @Test
    void deleteFromEmptyListDesc() {
        listDesc.delete(999);
        Assertions.assertEquals(0, listDesc.count(), "Failed deleteFromEmptyListAsc size");
    }

    @Test
    void deleteManyElementsAsc() {
        int size = 100;
        int deletingCount = (int) (Math.random() * 100);
        for (int i = 0; i < size; i++) {
            listAsc.add(i);
        }

        for (int i = 0; i < deletingCount; i++) {
            listAsc.delete(i);
        }

        Assertions.assertEquals(size - deletingCount, listAsc.count(), "Failed deleteManyElementsAsc size");
    }

    @Test
    void deleteManyElementsDesc() {
        int size = 100;
        int deletingCount = (int) (Math.random() * 100);
        for (int i = 0; i < size; i++) {
            listDesc.add(i);
        }

        for (int i = 0; i < deletingCount; i++) {
            listDesc.delete(i);
        }

        Assertions.assertEquals(size - deletingCount, listDesc.count(), "Failed deleteManyElementsDesc size");
    }

    @Test
    void deleteElementsAsc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add(i);
        }

        listAsc.delete(0);
        listAsc.delete(1);
        listAsc.delete(388);
        listAsc.delete(987);
        listAsc.delete(99);
        listAsc.delete(55);

        Assertions.assertEquals(size - 4, listAsc.count(), "Failed deleteElementsAsc size");
        Assertions.assertEquals(2, listAsc.head.value, "Failed deleteElementsAsc head value");
        Assertions.assertEquals(98, listAsc.tail.value, "Failed deleteElementsAsc tail value");
    }

    @Test
    void deleteElementsDesc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listDesc.add(i);
        }

        listDesc.delete(0);
        listDesc.delete(1);
        listDesc.delete(388);
        listDesc.delete(987);
        listDesc.delete(99);
        listDesc.delete(55);

        Assertions.assertEquals(size - 4, listDesc.count(), "Failed deleteElementsDesc size");
        Assertions.assertEquals(98, listDesc.head.value, "Failed deleteElementsDesc head value");
        Assertions.assertEquals(2, listDesc.tail.value, "Failed deleteElementsDesc tail value");
    }

    @Test
    void clear() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add(i);
        }

        for (int i = 0; i < size; i++) {
            listDesc.add(i);
        }

        listAsc.clear(false);
        listDesc.clear(true);

        Assertions.assertEquals(0, listAsc.count(), "Failed clear size asc");
        Assertions.assertEquals(0, listDesc.count(), "Failed clear size desc");

        for (int i = 0; i < size; i++) {
            listAsc.add(i);
        }

        for (int i = 0; i < size; i++) {
            listDesc.add(i);
        }

        Assertions.assertEquals(size, listAsc.count(), "Failed clear added elements size listAsc");
        Assertions.assertEquals(size, listDesc.count(), "Failed clear added elements size listDesc");

        Assertions.assertEquals(0, listDesc.head.value, "Failed revert desc head value");
        Assertions.assertEquals(99, listDesc.tail.value, "Failed revert desc tail value");

        Assertions.assertEquals(99, listAsc.head.value, "Failed revert asc head value");
        Assertions.assertEquals(0, listAsc.tail.value, "Failed revert asc tail value");
    }
}