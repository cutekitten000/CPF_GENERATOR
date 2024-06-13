package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CPFGenerator {
    private Random random;

    public CPFGenerator() {
        random = new Random();
    }

    public String generateCPF() {
        List<Integer> digits = generateInitialDigits();
        int firstDigit = calculateFirstDigit(digits);
        int secondDigit = calculateSecondDigit(digits, firstDigit);
        return formatCPF(digits, firstDigit, secondDigit);
    }

    private List<Integer> generateInitialDigits() {
        List<Integer> randomList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            randomList.add(random.nextInt(10));
        }

        return randomList;
    }

    private int calculateFirstDigit(List<Integer> digits) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits.get(i) * (10 - i);
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

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
