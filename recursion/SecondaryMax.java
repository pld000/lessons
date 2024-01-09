package recursion;

import java.util.ArrayList;

public class SecondaryMax {
    public static Integer getSecondMax(ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        } else if (numbers.size() == 1) {
            return numbers.get(0);
        } else if (numbers.size() == 2) {
            return Math.min(numbers.get(0), numbers.get(1));
        }

        int currentIndex = numbers.size() - 1;
        int maxNum = Math.max(numbers.get(currentIndex), numbers.get(currentIndex - 1));
        int secondMaxNum = Math.min(numbers.get(currentIndex), numbers.get(currentIndex - 1));

        return _getSecondMax(numbers, numbers.size() - 1, maxNum, secondMaxNum);
    }

    private static Integer _getSecondMax(ArrayList<Integer> numbers, int currentIndex, Integer maxNum, Integer secondMaxNum) {
        if (currentIndex == 1) {
            return secondMaxNum;
        }

        int testingNum = numbers.get(currentIndex - 2);

        if (maxNum >= testingNum) {
            secondMaxNum = Math.max(secondMaxNum, testingNum);
        } else {
            secondMaxNum = maxNum;
            maxNum = testingNum;
        }

        return _getSecondMax(numbers, --currentIndex, maxNum, secondMaxNum);
    }
}
