package dyn_array;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        DynArray<Integer> dyn = new DynArray<Integer>(Integer.class);

        for (int i = 0; i < 16; i++) {
            dyn.append(i + 16);
        }

        dyn.append(234);
        dyn.insert(999, 17);
        printDyn(dyn);

        dyn.remove(0);
        dyn.remove(2);
        dyn.remove(1);
        dyn.remove(0);
        printDyn(dyn);
        System.out.println("------------------------");
        System.out.println("Buffer: " + dyn.array.length);
    }

    public static void printDyn(DynArray<Integer> dyn) {
        System.out.println("-------------List START-----------");
        for (int i = 0; i < dyn.array.length; i++) {
            System.out.println(i + " " + dyn.array[i]);
        }
        System.out.println("-------------List END-----------");
    }
}
