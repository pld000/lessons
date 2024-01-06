package recursion;

import java.util.ArrayList;

public class EvenValuesPrint {
    public static void print(ArrayList<Integer> list) {
        if (list.isEmpty()) {
            return;
        }

        int value = list.get(0);
        if (value % 2 == 0) {
            System.out.println(value);
        }

        list.remove(0);
        print(list);
    }
}
