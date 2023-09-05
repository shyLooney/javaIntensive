package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number <= 1)
            throw new IllegalNumberException("Incorrect number");
        int count = 2;
        while (count * count <= number) {
            if (number % count == 0)
                return false;
            count++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;
        if (number < 0) {
            sum -= number % 10;
            number /= 10;
            number = -number;
        }
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }


    public class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String message) {
            super(message);
        }
    }
}
