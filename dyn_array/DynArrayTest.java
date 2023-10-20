package dyn_array;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {
    public DynArray<Integer> dynArray;
    private int _defaultBufferSize = 16;

    @BeforeEach
    void setUp() {
        this.dynArray = new DynArray<Integer>(Integer.class);

        for (int i = 0; i < this._defaultBufferSize; i++) {
            this.dynArray.append((int) (Math.random() * 100));
        }
    }

    @Test
    void testInsert() {
    }

    @Test()
    void testRemoveIndexOutOfBounds() {
//        try {
//            this.dynArrayEmpty.append(10);
//        } catch (IndexOutOfBoundsException e) {
//
//        }
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

        this.dynArray.append(((int) Math.random() * 100));

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