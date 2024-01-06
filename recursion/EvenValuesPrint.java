package recursion;

import java.util.ArrayList;

public class EvenValuesPrint {
    public static void print(ArrayList<Integer> list, int currentIndex) {
        if (currentIndex == list.size()) {
            return;
        }

        int value = list.get(currentIndex);
        if (value % 2 == 0) {
            System.out.println(value);
        }

        print(list, ++currentIndex);
    }
    public static void print(ArrayList<Integer> list) {
        print(list, 0);
    }
}
