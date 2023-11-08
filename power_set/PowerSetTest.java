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
    void get() {
    }

    @Test
    void remove() {
    }

    @Test
    void intersection() {
    }

    @Test
    void union() {
    }

    @Test
    void difference() {
    }

    @Test
    void isSubset() {

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