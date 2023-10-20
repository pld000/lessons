package dyn_array;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {
    public DynArray<Integer> dynArray;
    public DynArray<Integer> dynArrayPartial;
    public int someNumber;
    private int _defaultBufferSize = 16;
    private int _defaultPartialBufferSize = 9;

    @BeforeEach
    void setUp() {
        this.dynArray = new DynArray<Integer>(Integer.class);
        this.dynArrayPartial = new DynArray<Integer>(Integer.class);

        this.someNumber = (int) (Math.random() * 100);

        for (int i = 0; i < this._defaultBufferSize; i++) {
            this.dynArray.append((int) (Math.random() * 100));
        }

        for (int i = 0; i <= this._defaultPartialBufferSize; i++) {
            this.dynArrayPartial.append((int) (Math.random() * 100));
        }
    }


    @Test()
    void testInsertIndexOutOfBounds() {
        int wrongIndex = 999;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            this.dynArray.insert(this.someNumber, wrongIndex);
        });

        String expectedMessage = "Index out of range: " + wrongIndex;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void testInsertWithoutBufferChanging() {
        int bufferBefore = this.dynArrayPartial.array.length;
        int capacityBefore = this.dynArrayPartial.capacity;
        int countBefore = this.dynArrayPartial.count;

        int insertingIndex = this.dynArrayPartial.count;
        this.dynArrayPartial.insert(this.someNumber, insertingIndex);

        Assertions.assertEquals(this.someNumber, this.dynArrayPartial.getItem(insertingIndex), "Insert element to end, without buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferBefore, this.dynArrayPartial.array.length, "Insert element to end, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArrayPartial.capacity, "Insert element to end, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArrayPartial.count, "Insert element to end, without buffer changing. Wrong count!");

        insertingIndex = 0;
        this.dynArrayPartial.insert(this.someNumber, insertingIndex);

        Assertions.assertEquals(this.someNumber, this.dynArrayPartial.getItem(insertingIndex), "Insert element to start, without buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferBefore, this.dynArrayPartial.array.length, "Insert element to start, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArrayPartial.capacity, "Insert element to start, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArrayPartial.count, "Insert element to start, without buffer changing. Wrong count!");

        insertingIndex = 4;
        this.dynArrayPartial.insert(this.someNumber, insertingIndex);

        Assertions.assertEquals(this.someNumber, this.dynArrayPartial.getItem(insertingIndex), "Insert element form any position, without buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferBefore, this.dynArrayPartial.array.length, "Insert element form any position, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArrayPartial.capacity, "Insert element form any position, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArrayPartial.count, "Insert element form any position, without buffer changing. Wrong count!");
    }

    @Test()
    void testInsertWithBufferChanging() {
        int bufferBefore = this.dynArray.array.length;
        int capacityBefore = this.dynArray.capacity;
        int countBefore = this.dynArray.count;

        int insertingIndex = this.dynArray.count;
        this.dynArray.insert(this.someNumber, insertingIndex);

        int bufferAfter = bufferBefore * 2;
        int capacityAfter = capacityBefore * 2;

        Assertions.assertEquals(this.someNumber, this.dynArray.getItem(insertingIndex), "Insert element to end, with buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferAfter, this.dynArray.array.length, "Insert element to end, with buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityAfter, this.dynArray.capacity, "Insert element to end, with buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArray.count, "Insert element to end, with buffer changing. Wrong count!");

        insertingIndex = 0;
        this.dynArray.insert(this.someNumber, insertingIndex);

        Assertions.assertEquals(this.someNumber, this.dynArray.getItem(insertingIndex), "Insert element to start, with buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferAfter, this.dynArray.array.length, "Insert element to start, with buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityAfter, this.dynArray.capacity, "Insert element to start, with buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArray.count, "Insert element to start, with buffer changing. Wrong count!");

        insertingIndex = 4;
        this.dynArray.insert(this.someNumber, insertingIndex);

        Assertions.assertEquals(this.someNumber, this.dynArray.getItem(insertingIndex), "Insert element form any position, with buffer changing. Wrong element inserting!");
        Assertions.assertEquals(bufferAfter, this.dynArray.array.length, "Insert element form any position, with buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityAfter, this.dynArray.capacity, "Insert element form any position, with buffer changing. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArray.count, "Insert element form any position, with buffer changing. Wrong count!");
    }

    @Test()
    void testRemoveIndexOutOfBounds() {
        int wrongIndex = 999;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            this.dynArray.remove(wrongIndex);
        });

        String expectedMessage = "Index out of range: " + wrongIndex;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void testRemoveWithoutBufferChanging() {
        int removingIndex = 15;
        int bufferBefore = this.dynArray.array.length;
        int capacityBefore = this.dynArray.capacity;
        int countBefore = this.dynArray.count;

        this.dynArray.remove(removingIndex);
        Assertions.assertEquals(bufferBefore, this.dynArray.array.length, "Remove element form end, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArray.capacity, "Remove element form end, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(--countBefore, this.dynArray.count, "Remove element form end, without buffer changing. Wrong count!");

        removingIndex = 0;
        int nextElement = this.dynArray.getItem(removingIndex + 1);
        this.dynArray.remove(removingIndex);
        int afterRemoveElement = this.dynArray.getItem(removingIndex);

        Assertions.assertEquals(nextElement, afterRemoveElement, "Remove element form start, without buffer changing. Wrong element removing!");
        Assertions.assertEquals(bufferBefore, this.dynArray.array.length, "Remove element form start, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArray.capacity, "Remove element form start, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(--countBefore, this.dynArray.count, "Remove element form start, without buffer changing. Wrong count!");

        removingIndex = 7;
        nextElement = this.dynArray.getItem(removingIndex + 1);
        this.dynArray.remove(removingIndex);
        afterRemoveElement = this.dynArray.getItem(removingIndex);

        Assertions.assertEquals(nextElement, afterRemoveElement, "Remove element form any position, without buffer changing. Wrong element removing!");
        Assertions.assertEquals(bufferBefore, this.dynArray.array.length, "Remove element form any position, without buffer changing. Wrong buffer!");
        Assertions.assertEquals(capacityBefore, this.dynArray.capacity, "Remove element form any position, without buffer changing. Wrong capacity!");
        Assertions.assertEquals(--countBefore, this.dynArray.count, "Remove element form any position, without buffer changing. Wrong count!");
    }

    @Test()
    void testRemoveWithBufferChanging() {
        int bufferBefore = this.dynArray.array.length;
        int capacityBefore = this.dynArray.capacity;
        int countBefore = this.dynArray.count;

        this.dynArray.append((int) (Math.random() * 100));

        Assertions.assertEquals(bufferBefore * 2, this.dynArray.array.length, "Remove element, with buffer changing. Buffer extending. Wrong buffer!");
        Assertions.assertEquals(capacityBefore * 2, this.dynArray.capacity, "Remove element, with buffer changing. Buffer extending. Wrong capacity!");
        Assertions.assertEquals(++countBefore, this.dynArray.count, "Remove element, with buffer changing. Buffer extending. Wrong count!");

        int removingIndex = 7;
        bufferBefore = this.dynArray.array.length;
        capacityBefore = this.dynArray.capacity;
        countBefore = this.dynArray.count;

        int nextElement = this.dynArray.getItem(removingIndex + 1);
        this.dynArray.remove(removingIndex);
        int afterRemoveElement = this.dynArray.getItem(removingIndex);
        int bufferAfter = (int) (bufferBefore / 1.5);
        int capacityAfter = (int) (capacityBefore / 1.5);

        Assertions.assertEquals(nextElement, afterRemoveElement, "Remove element, buffer is reduced. Wrong element removing!");
        Assertions.assertEquals(bufferAfter, this.dynArray.array.length, "Remove element, buffer is reduced. Wrong buffer!");
        Assertions.assertEquals(capacityAfter, this.dynArray.capacity, "Remove element, buffer is reduced. Wrong capacity!");
        Assertions.assertEquals(--countBefore, this.dynArray.count, "Remove element, buffer is reduced. Wrong count!");
    }

    @AfterEach
    void tearDown() {
        this.dynArray = null;
    }
}