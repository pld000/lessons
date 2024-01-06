package recursion;

import java.util.ArrayList;

public class EvenIndexPrint {
    public static void print(ArrayList<Integer> list, int currentIndex) {
        if (currentIndex == list.size()) {
            return;
        } else if (currentIndex % 2 == 0) {
            System.out.println(list.get(currentIndex));
        }

        print(list, ++currentIndex);
    }
    public static void print(ArrayList<Integer> list) {
        print(list, 0);
    }
}
