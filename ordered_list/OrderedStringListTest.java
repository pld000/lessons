package ordered_list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderedStringListTest {
    public OrderedList<String> listAsc;
    public OrderedList<String> listDesc;

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
        Assertions.assertEquals(-1, listAsc.compare("a5", "a6"), "Failed compare less");
        Assertions.assertEquals(0, listAsc.compare("", ""), "Failed compare zeros");
        Assertions.assertEquals(1, listAsc.compare("a34", "a10"), "Failed compare greater");
        Assertions.assertEquals(0, listAsc.compare("a99", "a99"), "Failed compare equals");
    }

    @Test
    void addOneElementAsc() {
        String someValue = "  a99  ";
        listAsc.add(someValue);
        Assertions.assertEquals(1, listAsc.count(), "Failed addOneElementAsc");
        Assertions.assertEquals(someValue, listAsc.head.value, "Failed addOneElementAsc head value");
        Assertions.assertEquals(someValue, listAsc.tail.value, "Failed addOneElementAsc tail value");
    }

    @Test
    void addOneElementDesc() {
        String someValue = "99";
        listDesc.add(someValue);
        Assertions.assertEquals(1, listDesc.count(), "Failed addOneElementDesc");
        Assertions.assertEquals(someValue, listDesc.head.value, "Failed addOneElementDesc head value");
        Assertions.assertEquals(someValue, listDesc.tail.value, "Failed addOneElementDesc tail value");
    }

    @Test
    void addManyElementsAsc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add("a" + i);
        }

        Assertions.assertEquals(size, listAsc.count(), "Failed addManyElementsAsc size");
        Assertions.assertEquals("a0", listAsc.head.value, "Failed addManyElementsAsc head value");
        Assertions.assertEquals("a99", listAsc.tail.value, "Failed addManyElementsAsc tail value");
    }

    @Test
    void addManyElementsDesc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listDesc.add("a" + i);
        }

        Assertions.assertEquals(size, listDesc.count(), "Failed addManyElementsDesc size");
        Assertions.assertEquals("a99", listDesc.head.value, "Failed addManyElementsDesc head value");
        Assertions.assertEquals("a0", listDesc.tail.value, "Failed addManyElementsDesc tail value");
    }

    @Test
    void addElementsAsc() {
        String[] testValues = {"a23", "a54", "a0", "a4", "a26", "a11", "a5", "a999", "a32", "a56", "a26", "a1", "a47", "a81"};
        for (int i = 0; i < testValues.length; i++) {
            listAsc.add(testValues[i]);
        }

        Assertions.assertEquals(testValues.length, listAsc.count(), "Failed addElementsAsc size");
        Assertions.assertEquals("a0", listAsc.head.value, "Failed addElementsAsc head value");
        Assertions.assertEquals("a999", listAsc.tail.value, "Failed addElementsAsc tail value");
    }

    @Test
    void addElementsDesc() {
        String[] testValues = {"a23", "a54", "a0", "a4", "a26", "a11", "a5", "a999", "a32", "a56", "a26", "a1", "a47", "a81"};
        for (int i = 0; i < testValues.length; i++) {
            listDesc.add(testValues[i]);
        }

        Assertions.assertEquals(testValues.length, listDesc.count(), "Failed addElementsDesc size");
        Assertions.assertEquals("a999", listDesc.head.value, "Failed addElementsDesc head value");
        Assertions.assertEquals("a0", listDesc.tail.value, "Failed addElementsDesc tail value");
    }

    @Test
    void findInEmptyListAsc() {
        var node = listAsc.find("a999");
        Assertions.assertNull(node, "Failed findInEmptyListAsc");
    }

    @Test
    void findInEmptyListDesc() {
        var node = listDesc.find("a999");
        Assertions.assertNull(node, "Failed findInEmptyListDesc");
    }

    @Test
    void findAnyElementAsc() {
        listAsc.add("a44");
        listAsc.add("a99");
        listAsc.add("a5");
        listAsc.add("a23");
        listAsc.add("a60");

        var node = listAsc.find("a99");
        Assertions.assertEquals("a99", node.value, "Failed findAnyElementAsc");

        node = listAsc.find("a999");
        Assertions.assertNull(node, "Failed findAnyElementAsc not exist");
    }

    @Test
    void findAnyElementDesc() {
        listDesc.add("a44");
        listDesc.add("a99");
        listDesc.add("a5");
        listDesc.add("a23");
        listDesc.add("a60");

        var node = listDesc.find("a44");
        Assertions.assertEquals("a44", node.value, "Failed findAnyElementDesc");

        var node2 = listDesc.find("a999");
        Assertions.assertNull(node2, "Failed findAnyElementDesc not exist");
    }

    @Test
    void deleteFromEmptyListAsc() {
        listAsc.delete("999");
        Assertions.assertEquals(0, listDesc.count(), "Failed deleteFromEmptyListAsc size");
    }

    @Test
    void deleteFromEmptyListDesc() {
        listDesc.delete("999");
        Assertions.assertEquals(0, listDesc.count(), "Failed deleteFromEmptyListAsc size");
    }

    @Test
    void deleteManyElementsAsc() {
        int size = 100;
        int deletingCount = (int) (Math.random() * 100);
        for (int i = 0; i < size; i++) {
            listAsc.add("a" + i);
        }

        for (int i = 0; i < deletingCount; i++) {
            listAsc.delete("a" + i);
        }

        Assertions.assertEquals(size - deletingCount, listAsc.count(), "Failed deleteManyElementsAsc size");
    }

    @Test
    void deleteManyElementsDesc() {
        int size = 100;
        int deletingCount = (int) (Math.random() * 100);
        for (int i = 0; i < size; i++) {
            listDesc.add("a" + i);
        }

        for (int i = 0; i < deletingCount; i++) {
            listDesc.delete("a" + i);
        }

        Assertions.assertEquals(size - deletingCount, listDesc.count(), "Failed deleteManyElementsDesc size");
    }

    @Test
    void deleteElementsAsc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add("a" + i);
        }

        listAsc.delete("a0");
        listAsc.delete("a10");
        listAsc.delete("a388");
        listAsc.delete("a987");
        listAsc.delete("a99");
        listAsc.delete("a55");

        Assertions.assertEquals(size - 4, listAsc.count(), "Failed deleteElementsAsc size");
        Assertions.assertEquals("a1", listAsc.head.value, "Failed deleteElementsAsc head value");
        Assertions.assertEquals("a98", listAsc.tail.value, "Failed deleteElementsAsc tail value");

        listAsc.add("a55");
        listAsc.add("a55");

        listAsc.delete("a55");
        Node<String> node = listAsc.find("a55");
        Assertions.assertEquals("a55", node.value, "Failed deleteElementsAsc duplicate value");
        listAsc.delete("a55");
        node = listAsc.find("a55");
        Assertions.assertNull(node, "Failed deleteElementsAsc duplicate value");
    }

    @Test
    void deleteElementsDesc() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listDesc.add("a" + i);
        }

        listDesc.delete("a0");
        listDesc.delete("a2");
        listDesc.delete("a388");
        listDesc.delete("a987");
        listDesc.delete("a99");
        listDesc.delete("a55");

        Assertions.assertEquals(size - 4, listDesc.count(), "Failed deleteElementsDesc size");
        Assertions.assertEquals("a98", listDesc.head.value, "Failed deleteElementsDesc head value");
        Assertions.assertEquals("a1", listDesc.tail.value, "Failed deleteElementsDesc tail value");


        listDesc.add("a55");
        listDesc.add("a55");

        listDesc.delete("a55");
        Node<String> node = listDesc.find("a55");
        Assertions.assertEquals("a55", node.value, "Failed deleteElementsDesc duplicate value");
        listDesc.delete("a55");
        node = listDesc.find("a55");
        Assertions.assertNull(node, "Failed deleteElementsDesc duplicate value");
    }

    @Test
    void clear() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            listAsc.add("a" + i);
        }

        for (int i = 0; i < size; i++) {
            listDesc.add("a" + i);
        }

        listAsc.clear(false);
        listDesc.clear(true);

        Assertions.assertEquals(0, listAsc.count(), "Failed clear size asc");
        Assertions.assertEquals(0, listDesc.count(), "Failed clear size desc");

        for (int i = 0; i < size; i++) {
            listAsc.add("a" + i);
        }

        for (int i = 0; i < size; i++) {
            listDesc.add("a" + i);
        }

        Assertions.assertEquals(size, listAsc.count(), "Failed clear added elements size listAsc");
        Assertions.assertEquals(size, listDesc.count(), "Failed clear added elements size listDesc");

        Assertions.assertEquals("a0", listDesc.head.value, "Failed revert desc head value");
        Assertions.assertEquals("a99", listDesc.tail.value, "Failed revert desc tail value");

        Assertions.assertEquals("a99", listAsc.head.value, "Failed revert asc head value");
        Assertions.assertEquals("a0", listAsc.tail.value, "Failed revert asc tail value");
    }
}