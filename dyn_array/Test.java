package dyn_array;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        DynArray<Integer> dyn = new DynArray<Integer>(Integer.class);

        for (int i = 0; i < 16; i++) {
            dyn.append(i + 1);
        }

//        for (int i = 0; i < 16; i++) {
//            dyn.remove(0);
//        }

        for (int i = 15; i >= 0; i--) {
            dyn.remove(0);
        }

     //   dyn.remove(15);

        printDyn(dyn);
        System.out.println("Length: " + dyn.array.length);
        System.out.println("Capacity: " + dyn.capacity);
        System.out.println("Count: " + dyn.count);
    }

    public static void printDyn(DynArray<Integer> dyn) {
        System.out.println("-------------List START-----------");
        for (int i = 0; i < dyn.array.length; i++) {
            System.out.println(i + " " + dyn.array[i]);
        }
        System.out.println("-------------List END-----------");
    }
}
