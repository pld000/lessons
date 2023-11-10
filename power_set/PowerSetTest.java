package power_set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

class PowerSetTest {
    public PowerSet powerSet;

    @BeforeEach
    void setUp() {
        powerSet = new PowerSet();
    }

    @AfterEach
    void tearDown() {
        powerSet = null;
    }

    @Test
    void size() {
    }

    @Test
    void put() {
        String someValue = "some_value";
        powerSet.put(someValue);
        int slotBefore = powerSet._findSlot(someValue);
        Assertions.assertEquals(1, powerSet.size(), "Failed put->size someValue to empty");

        powerSet.put(someValue);
        int slotAfter = powerSet._findSlot(someValue);
        Assertions.assertEquals(1, powerSet.size(), "Failed put(the same value)->size someValue to empty");
        Assertions.assertEquals(slotBefore, slotAfter, "Failed put(the same value) slot");
    }

    @Test
    void putForFull() {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            String genStr = getRandomString();
            arr.add(genStr);
            powerSet.put(genStr);
        }
        Assertions.assertEquals(20000, powerSet.size(), "Failed put for full");

        String someValue = arr.get((int) (Math.random() * 1000));
        Integer someValueSlot = powerSet._findSlot(someValue);
        Assertions.assertTrue(powerSet.get(someValue), "Failed putForFull->get someValue exist");
        Assertions.assertFalse(powerSet.get(someValue + "Any_string"), "Failed putForFull->get someValue doesn't exist");

        powerSet.remove(someValue);
        Assertions.assertEquals(19999, powerSet.size(), "putForFull->remove->size");
        Assertions.assertEquals(-1, powerSet._findSlot(someValue), "putForFull->remove->_findSlot");
        Assertions.assertNull(powerSet.values[someValueSlot], "putForFull->remove->values[someValueSlot]");
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
        Boolean res = powerSet.remove("Any_testing_string");
        Assertions.assertFalse(res, "remove from empty");
        Assertions.assertEquals(0, powerSet.size(), "removeEmpty->size");

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            String genStr = getRandomString();
            arr.add(genStr);
            powerSet.put(genStr);
        }

        powerSet.remove(arr.get(0));
        powerSet.remove(arr.get(3425));
        powerSet.remove(arr.get(1934));
        powerSet.remove(arr.get(15894));
        powerSet.remove(arr.get(18764));
        powerSet.remove(arr.get(2034));
        powerSet.remove(arr.get(1111));
        powerSet.remove(arr.get(8372));

        Assertions.assertEquals(19992, powerSet.size(), "remove a few elements -> size");
    }

    @Test
    void intersection() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        String[] setValues1 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] setValues2 = {"s1", "s22", "s3", "s44", "s5", "s6", "s77", "s8", "s9"};
        String[] intersectionValues = {"s1", "s3", "s5", "s6", "s8", "s9"};

        PowerSet emptySet = set1.intersection(set2);
        Assertions.assertEquals(0, emptySet.size(), "empty size");

        for (int i = 0; i < setValues1.length; i++) {
            set1.put(setValues1[i]);
        }

        PowerSet emptyIntersectionSet = set1.intersection(set2);
        Assertions.assertEquals(0, emptyIntersectionSet.size(), "emptyIntersectionSet size");

        for (int i = 0; i < setValues2.length; i++) {
            set2.put(setValues2[i]);
        }

        PowerSet intersectionSet = set1.intersection(set2);
        Assertions.assertEquals(intersectionValues.length, intersectionSet.size(), "intersection size");

        for (int i = 0; i < intersectionValues.length; i++) {
            Assertions.assertTrue(intersectionSet.get(intersectionValues[i]), "intersection get " + i);
        }
    }

    @Test
    void intersectionEmptyResult() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        String[] setValues1 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] setValues2 = {"s11", "s22", "s33", "s44", "s53", "s63", "s77", "s83", "s93"};

        for (int i = 0; i < setValues1.length; i++) {
            set1.put(setValues1[i]);
        }

        for (int i = 0; i < setValues2.length; i++) {
            set2.put(setValues2[i]);
        }

        PowerSet intersectionSet = set1.intersection(set2);
        Assertions.assertEquals(0, intersectionSet.size(), "intersectionEmptyResult size");
    }

    @Test
    void union() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        String[] setValues1 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] setValues2 = {"s1", "s22", "s3", "s44", "s5", "s6", "s77", "s8", "s9"};
        String[] unionValues = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s22", "s44", "s77"};

        PowerSet emptySet = set1.union(set2);
        Assertions.assertEquals(0, emptySet.size(), "union empty size");

        for (int i = 0; i < setValues1.length; i++) {
            set1.put(setValues1[i]);
        }

        PowerSet emptyUnionSet = set1.union(set2);
        Assertions.assertEquals(setValues1.length, emptyUnionSet.size(), "emptyUnionSet size");

        for (int i = 0; i < setValues2.length; i++) {
            set2.put(setValues2[i]);
        }

        PowerSet twoUnionSet = set1.union(set2);
        Assertions.assertEquals(unionValues.length, twoUnionSet.size(), "twoUnion size");

        for (int i = 0; i < unionValues.length; i++) {
            Assertions.assertTrue(twoUnionSet.get(unionValues[i]), "twoUnion get " + i);
        }
    }

    @Test
    void difference() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        String[] setValues1 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] setValues2 = {"s1", "s22", "s3", "s44", "s5", "s6", "s77", "s8", "s9"};
        String[] differenceValues = {"s2", "s4", "s7"};

        PowerSet emptySet = set1.difference(set2);
        Assertions.assertEquals(0, emptySet.size(), "difference empty size");

        for (int i = 0; i < setValues1.length; i++) {
            set1.put(setValues1[i]);
        }

        PowerSet emptyDifferenceSet = set1.difference(set2);
        Assertions.assertEquals(emptyDifferenceSet.size(), set1.size(), "emptyDifferenceSet size");

        for (int i = 0; i < setValues2.length; i++) {
            set2.put(setValues2[i]);
        }

        PowerSet differenceSet = set1.difference(set2);
        Assertions.assertEquals(differenceValues.length, differenceSet.size(), "differenceSet size");

        for (int i = 0; i < differenceValues.length; i++) {
            Assertions.assertTrue(differenceSet.get(differenceValues[i]), "differenceSet get " + i);
        }
    }

    @Test
    void isSubset() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        PowerSet set3 = new PowerSet();
        PowerSet set4 = new PowerSet();

        String[] setValues1 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] setValues2 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11"};
        String[] setValues3 = {"s2", "s4", "s7"};
        String[] setValues4 = {"s1", "s22", "s3"};

        set1.isSubset(set2);
        Assertions.assertTrue(set1.isSubset(set2), "isSubset empty sets");

        for (int i = 0; i < setValues1.length; i++) {
            set1.put(setValues1[i]);
        }
        Assertions.assertTrue(set1.isSubset(set2), "isSubset not empty with empty");

        for (int i = 0; i < setValues2.length; i++) {
            set2.put(setValues2[i]);
        }
        Assertions.assertTrue(set2.isSubset(set1), "set1 is subset of set2");
        Assertions.assertFalse(set1.isSubset(set2), "set2 is not subset of set1");

        for (int i = 0; i < setValues3.length; i++) {
            set3.put(setValues3[i]);
        }
        for (int i = 0; i < setValues4.length; i++) {
            set4.put(setValues4[i]);
        }

        Assertions.assertTrue(set1.isSubset(set3), "set3 is subset of set1");
        Assertions.assertFalse(set1.isSubset(set4), "set4 is not subset of set1");
    }

    @Test
    void someTEst() {
        int collisions = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            String genStr = getRandomString();
            int hash = powerSet._hashFun(genStr);

            if (arr.contains(hash)) collisions++;

            arr.add(hash);
            //   System.out.print(hash + ", ");
//            System.out.print(genStr + ", ");
        }
        System.out.println(collisions + " collisions");
    }

    public String getRandomString() {
        int leftLimit = 48; // цифра '0'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = 40;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
//        return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}