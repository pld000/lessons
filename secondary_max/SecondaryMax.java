package secondary_max;

public class SecondaryMax {
    public int getSecondMax(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        } else if (numbers.length == 1) {
            return numbers[0];
        } else if (numbers.length == 2) {
            return Math.min(numbers[0], numbers[1]);
        }

        int minNumber = Math.min(numbers[0], Math.min(numbers[1], numbers[2]));
        int[] reducedNumbers = new int[numbers.length - 1];

        if (numbers[0] == minNumber) {
            reducedNumbers[0] = numbers[1];
            reducedNumbers[1] = numbers[2];
        } else if (numbers[1] == minNumber) {
            reducedNumbers[0] = numbers[0];
            reducedNumbers[1] = numbers[2];
        } else {
            reducedNumbers[0] = numbers[0];
            reducedNumbers[1] = numbers[1];
        }

        for (int i = 3; i < numbers.length; i++) {
            reducedNumbers[i - 1] = numbers[i];
        }

        return getSecondMax(reducedNumbers);
    }
}
