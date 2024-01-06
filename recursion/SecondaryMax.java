package recursion;

import java.util.ArrayList;

public class SecondaryMax {
    public static int getSecondMax(ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.size() == 1) {
            return numbers.get(0);
        } else if (numbers.size() == 2) {
            return Math.min(numbers.get(0), numbers.get(1));
        }

        int minNumber = Math.min(numbers.get(0), Math.min(numbers.get(1), numbers.get(2)));
        int removingIndex = numbers.get(0) == minNumber ? 0 : numbers.get(1) == minNumber ? 1 : 2;
        numbers.remove(removingIndex);

        return getSecondMax(numbers);
    }
}
