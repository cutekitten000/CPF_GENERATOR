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
