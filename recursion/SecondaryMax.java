package recursion;

import java.util.ArrayList;

public class SecondaryMax {
    public static int getSecondMax(ArrayList<Integer> numbers, int currentIndex) {
        if (currentIndex == numbers.size() - 1) {
            return numbers.get(numbers.size() - 2);
        }

        int nextIndex = currentIndex + 1;
        int nextNextIndex = currentIndex + (currentIndex == numbers.size() - 2 ? 1 : 2);

        int min = Math.min(numbers.get(currentIndex), Math.min(numbers.get(nextIndex), numbers.get(nextNextIndex)));
        int replacingIndex = numbers.get(currentIndex) == min ? currentIndex : numbers.get(nextIndex) == min ? nextIndex : nextNextIndex;

        numbers.set(replacingIndex, numbers.get(currentIndex));
        numbers.set(currentIndex, min);

        return getSecondMax(numbers, ++currentIndex);
    }

    public static int getSecondMax(ArrayList<Integer> numbers) {
        return getSecondMax(numbers, 0);
    }
}
