package recursion;

public class Exponent {
    public double pow(double base, double exponent) {
        return exponent == 0 ? 1 : base * pow(base, exponent - 1);
    }
}
