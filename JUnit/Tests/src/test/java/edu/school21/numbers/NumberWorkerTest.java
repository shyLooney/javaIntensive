package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker nb = new NumberWorker();
    @ParameterizedTest(name = "{index}. number = {0}")
    @ValueSource(ints = { 2, 2741, 991 })
    public void isPrimeForPrimes(int number) {
        Assertions.assertEquals(nb.isPrime(number), true);
    }

    @ParameterizedTest(name = "{index}. number = {0}")
    @ValueSource(ints = { 4, 200, 7513081 })
    public void isPrimeForNotPrimes(int number) {
        Assertions.assertEquals(nb.isPrime(number), false);
    }

    @ParameterizedTest(name = "{index}. number = {0}")
    @ValueSource(ints = { 1, 0, -7513081 })
    public void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class,() -> nb.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void checkDigitSum(int number, int result) {
        Assertions.assertEquals(nb.digitsSum(number), result);
    }
}
