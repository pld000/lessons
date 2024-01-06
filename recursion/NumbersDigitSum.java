package recursion;

public class NumbersDigitSum {
    public static int getSum(double number) {
        if (number == 0) {
            return 0;
        }

        int digit = (int) number % 10;
        return digit + getSum((number - digit) / 10);
    }
}
