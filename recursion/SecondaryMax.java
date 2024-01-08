package recursion;

import java.util.ArrayList;

public class SecondaryMax {
    public static Integer getSecondMax(ArrayList<Integer> numbers, int currentIndex, int maxNum, int secondMaxNum) {
        if (currentIndex == 0) {
            return secondMaxNum;
        }

        if (maxNum < numbers.get(currentIndex)) {
            secondMaxNum = maxNum;
            maxNum = numbers.get(currentIndex);
        } else {
            secondMaxNum = Math.max(secondMaxNum, numbers.get(currentIndex));
        }

        return getSecondMax(numbers, --currentIndex, maxNum, secondMaxNum);
    }

    public static Integer getSecondMax(ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        } else if (numbers.size() == 1) {
            return numbers.get(0);
        }

        int currentIndex = numbers.size() - 1;
        int maxNum = Math.max(numbers.get(currentIndex), numbers.get(currentIndex - 1));
        int secondMaxNum = Math.min(numbers.get(currentIndex), numbers.get(currentIndex - 1));

        return getSecondMax(numbers, currentIndex - 1, maxNum, secondMaxNum);
    }
}
