package dyn_array;

public class Test {
    public static void main(String[] args) {
        DynArray<Integer> dyn = new DynArray<Integer>(Integer.class);

        dyn.append(32);
        dyn.append(32);
        dyn.append(32);
        dyn.append(32);
        dyn.append(32);

        //System.out.println(dyn + " result");
System.out.println(dyn.getItem(2));
        printDyn(dyn);
    }

    public static void printDyn(DynArray<Integer> dyn) {
        for (int i = 0; i < dyn.array.length; i++) {
            System.out.println(i + " " + dyn.array[i]);
        }
    }
}
