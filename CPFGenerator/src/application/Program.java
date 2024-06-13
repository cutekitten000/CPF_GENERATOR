package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import entities.CPFGenerator;

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
                        System.out.println("exiting...");
                        return;
                
                    default:
                        System.out.println("Invalid option. Try again!!");
                        break;
                }
                
            }
        } catch (InputMismatchException e) {
            System.out.println("String not allowed, just integer numbers" + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void printMenu() {
        System.out.println("-------------------------------");
        System.out.println("|         CPF GENERATOR       |");
        System.out.println("-------------------------------");
        System.out.println("| [ 1 ] - Generate new CPF    |");
        System.out.println("| [ 2 ] - Exit                |");
        System.out.println("-------------------------------");
    }

    private static void generateCPF() {
        CPFGenerator cpf = new CPFGenerator();
        System.out.println();
        System.out.println("CPF: " + cpf.generateCPF());
    }
}
