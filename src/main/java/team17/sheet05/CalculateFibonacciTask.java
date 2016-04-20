package team17.sheet05;

import java.io.Serializable;

public class CalculateFibonacciTask implements Task<Integer>, Serializable {

    private int number;

    public CalculateFibonacciTask(int n) {
        number = n;
    }

    @Override
    public Integer execute() {
        return getFibonacciNumber(number);
    }

    private Integer getFibonacciNumber(int x) {

        int result;
        if (x == 1 || x == 2) {
            result = 1;
        } else {
            result= getFibonacciNumber(x - 1) + getFibonacciNumber(x - 2);
        }

        return result;
    }
}
