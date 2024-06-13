package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The CPFGenerator class provides methods to generate a valid CPF number.
 */
public class CPFGenerator {
    private final Random random;

    /**
     * Constructs a CPFGenerator with a new Random instance.
     */
    public CPFGenerator() {
        this.random = new Random();
    }

    /**
     * Generates a valid CPF number.
     * @return A formatted CPF number as a String.
     */
    public String generateCPF() {
        List<Integer> digits = generateInitialDigits();
        int firstDigit = calculateFirstDigit(digits);
        int secondDigit = calculateSecondDigit(digits, firstDigit);
        return formatCPF(digits, firstDigit, secondDigit);
    }

    /**
     * Generates the initial 9 digits of the CPF number.
     * @return A list of 9 random integers.
     */
    private List<Integer> generateInitialDigits() {
        List<Integer> randomList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            randomList.add(random.nextInt(10));
        }

        return randomList;
    }

    /**
     * Calculates the first verification digit of the CPF number.
     * @param digits The initial 9 digits of the CPF number.
     * @return The first verification digit.
     */
    private int calculateFirstDigit(List<Integer> digits) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits.get(i) * (10 - i);
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    /**
     * Calculates the second verification digit of the CPF number.
     * @param digits The initial 9 digits of the CPF number.
     * @param firstDigit The first verification digit of the CPF number.
     * @return The second verification digit.
     */
    private int calculateSecondDigit(List<Integer> digits, int firstDigit) {
        List<Integer> extendedDigits = new ArrayList<>(digits);
        extendedDigits.add(firstDigit);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += extendedDigits.get(i) * (11 - i);
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    /**
     * Formats the CPF number as a string with the standard CPF format.
     * @param digits The initial 9 digits of the CPF number.
     * @param firstDigit The first verification digit.
     * @param secondDigit The second verification digit.
     * @return The formatted CPF number as a String.
     */
    private String formatCPF(List<Integer> digits, int firstDigit, int secondDigit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                sb.append('.');
            }
            sb.append(digits.get(i));
        }
        sb.append("-");
        sb.append(firstDigit);
        sb.append(secondDigit);

        return sb.toString();
    }
}