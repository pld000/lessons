package recursion;

import java.util.ArrayList;

public class EvenIndexPrint {
    public static void print(ArrayList<Integer> list, int currentIndex) {
        if (currentIndex >= list.size()) {
            return;
        }

        System.out.println(list.get(currentIndex));
        print(list, currentIndex + 2);
    }

    public static void print(ArrayList<Integer> list) {
        print(list, 0);
    }
}
