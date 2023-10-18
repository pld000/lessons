package dyn_array;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
//        ArrayList<Integer> arrayListLarge = new ArrayList<Integer>(5);
//        arrayListLarge.add(0, 32);
//        arrayListLarge.add(1, 32);
//        arrayListLarge.add(2, 32);
//        arrayListLarge.add(3, 32);
//        arrayListLarge.add(2, 99);
//        for (int z = 0; z < arrayListLarge.size(); z++) {
//            System.out.println(arrayListLarge.get(z));
//        }

        DynArray<Integer> dyn = new DynArray<Integer>(Integer.class);

        for (int i = 0; i < 16; i++) {
            dyn.append(i + 5);
        }

//        dyn.insert(999, 17);

        //   System.out.println("Get item " + dyn.getItem(2));
        printDyn(dyn);

//        dyn.remove(0);
//        printDyn(dyn);
    }

    public static void printDyn(DynArray<Integer> dyn) {
        for (int i = 0; i < dyn.array.length; i++) {
            System.out.println(i + " " + dyn.array[i]);
        }
    }
}
