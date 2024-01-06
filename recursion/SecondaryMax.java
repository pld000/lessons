package recursion;

import java.util.ArrayList;

public class SecondaryMax {
    public static int getSecondMax(ArrayList<Integer> numbers, int currentIndex) {
        if (currentIndex == 0) {
            return numbers.get(1);
        }

        int prevIndex = Math.max(currentIndex - 1, 0);
        int prevPrevIndex = Math.max(currentIndex - 2, 0);

        int min = Math.min(numbers.get(currentIndex), Math.min(numbers.get(prevIndex), numbers.get(prevPrevIndex)));
        int replacingIndex = numbers.get(currentIndex) == min ? currentIndex : numbers.get(prevIndex) == min ? prevIndex : prevPrevIndex;

        numbers.set(replacingIndex, numbers.get(currentIndex));
        numbers.set(currentIndex, min);

        return getSecondMax(numbers, --currentIndex);
    }

    public static int getSecondMax(ArrayList<Integer> numbers) {
        return getSecondMax(numbers, numbers.size() - 1);
    }
}
