package power_set;

import org.junit.jupiter.api.AfterEach;
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
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}