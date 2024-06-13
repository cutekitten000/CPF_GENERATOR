
# CPF Generator

CPF Generator is a Java-based application that generates valid Brazilian CPF (Cadastro de Pessoas Físicas) numbers. This project includes a command-line interface (CLI) that allows users to generate new CPF numbers and exit the application.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Code Structure](#code-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The CPF Generator project is designed to help users generate valid CPF numbers for testing and educational purposes. The CPF (Cadastro de Pessoas Físicas) is a unique identifier assigned to Brazilian citizens and resident aliens for tax purposes.

## Features

- Generate valid CPF numbers.
- Simple command-line interface (CLI).
- Error handling for invalid inputs.

## Getting Started

### Prerequisites

To run this project, you need to have the following installed:

- Java Development Kit (JDK) 8 or later
- Apache Maven (optional, for building the project)

### Installation

1. **Clone the repository:**

   ```sh
   git clone https://github.com/yourusername/CPF-Generator.git
   cd CPF-Generator
   ```

2. **Compile the project:**

   If you are using Maven:

   ```sh
   mvn clean compile
   ```

   Or you can compile it directly using the JDK:

   ```sh
   javac -d bin src/application/Program.java src/entities/CPFGenerator.java
   ```

3. **Run the application:**

   If you are using Maven:

   ```sh
   mvn exec:java -Dexec.mainClass="application.Program"
   ```

   Or you can run it directly using the JDK:

   ```sh
   java -cp bin application.Program
   ```

## Usage

After starting the application, you will be presented with a menu:

```
-------------------------------
|         CPF GENERATOR       |
-------------------------------
| [ 1 ] - Generate new CPF    |
| [ 2 ] - Exit                |
-------------------------------
```

### Options:

1. **Generate new CPF**: Select this option to generate and display a new CPF number.
2. **Exit**: Select this option to exit the application.

### Example

To generate a new CPF, enter `1` and press `Enter`. The application will display a generated CPF number. To exit the application, enter `2` and press `Enter`.

## Code Structure

The project consists of two main parts:

1. **Main Application (`Program.java`)**:
   - Contains the `main` method that serves as the entry point of the application.
   - Displays the menu and handles user input.
   - Calls the `CPFGenerator` to generate new CPF numbers.

2. **CPF Generator (`CPFGenerator.java`)**:
   - Contains the logic for generating valid CPF numbers.
   - Generates the initial 9 digits of the CPF number.
   - Calculates the verification digits.
   - Formats the CPF number as a string.

### Detailed Explanation of Classes

#### `Program.java`

```java
package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import entities.CPFGenerator;

/**
 * The Program class is the entry point of the CPF Generator application.
 * It provides a menu-driven interface for generating new CPF numbers.
 */
public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                printMenu();
                System.out.println();
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
    
                switch (choice) {
                    case 1:
                        generateCPF();
                        break;
    
                    case 2:
                        System.out.println("Exiting...");
                        return;
                
                    default:
                        System.out.println("Invalid option. Try again!!");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Only integer numbers are allowed.");
            sc.next();  // Clear the invalid input
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

    /**
     * Prints the menu options to the console.
     */
    private static void printMenu() {
        System.out.println("-------------------------------");
        System.out.println("|         CPF GENERATOR       |");
        System.out.println("-------------------------------");
        System.out.println("| [ 1 ] - Generate new CPF    |");
        System.out.println("| [ 2 ] - Exit                |");
        System.out.println("-------------------------------");
    }

    /**
     * Generates a new CPF number and prints it to the console.
     */
    private static void generateCPF() {
        CPFGenerator cpf = new CPFGenerator();
        System.out.println();
        System.out.println("CPF: " + cpf.generateCPF());
    }
}
```

#### `CPFGenerator.java`

```java
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
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
