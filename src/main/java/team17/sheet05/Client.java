package team17.sheet05;

import team17.sheet05.helpers.CalculatePiTask;
import team17.sheet05.helpers.KeyIn;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static team17.sheet05.helpers.KeyIn.inInt;


public class Client {

    private static ICalculator calculator;
    private static RemoteTask taskRunner;

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            calculator = (ICalculator) registry.lookup("ICalculator");
            taskRunner = (RemoteTask) registry.lookup("RemoteTask");

            run();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static int getChoice() {

        // Display menu
        System.out.println("===========================");
        System.out.println("| Remote Calculation Demo |");
        System.out.println("===========================");
        System.out.println("| Methods:                |");
        System.out.println("| 1. Add numbers          |");
        System.out.println("| 2. Subtract numbers     |");
        System.out.println("| 3. Multiply numbers     |");
        System.out.println("| 4. Lucas number         |");
        System.out.println("| 5. PI (Task)            |");
        System.out.println("| 6. Fibonacci (Task)     |");
        System.out.println("|                         |");
        System.out.println("| Settings:               |");
        System.out.println("| 9. Exit                 |");
        System.out.println("===========================");

        // Read choice
        return inInt(" Select option: ");
    }

    private static void run() {

        int choice;
        do {

            choice = getChoice();

            // Switch construct
            switch (choice) {
                case 1:
                    AddNumbers();
                    break;
                case 2:
                    SubtractNumbers();
                    break;
                case 3:
                    MultiplyNumbers();
                    break;
                case 4:
                    LucasNumber();
                    break;
                case 5:
                    CalculatePi();
                    break;
                case 6:
                    FibonacciNumber();
                    break;
                case 7:
                    DeepThought();
                    break;
                case 9:
                    System.out.println("Exit selected");
                    break;
                default:
                    System.out.println("Invalid selection");
            }

        } while (choice != 9);
    }

    private static void DeepThought() {

    }

    private static void FibonacciNumber() {
        try {
            int a = inInt("Number:");
            CalculateFibonacciTask task = new CalculateFibonacciTask(a);
            Integer fib = taskRunner.execute(task);

            System.out.printf("The fibonacci number %d is %d\n\n", a, fib);
        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void CalculatePi() {
        try {
            int digits = inInt("How may digits?:");
            CalculatePiTask task = new CalculatePiTask(digits);
            BigDecimal pi = taskRunner.execute(task);

            System.out.printf("Pi is %s\n\n", pi.toString());

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }

    }

    private static void LucasNumber() {
        try {

            int a = inInt("Number:");

            int result = calculator.Lukas(a);

            System.out.format("Lucas number %d = %d\n\n", a, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void MultiplyNumbers() {
        try {

            int a = inInt("First number:");
            int b = inInt("Second number:");

            int result = calculator.Multiply(a, b);

            System.out.format("%d * %d = %d\n\n", a, b, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void SubtractNumbers() {
        try {

            int a = inInt("First number:");
            int b = inInt("Second number:");

            int result = calculator.Subtract(a, b);

            System.out.format("%d - %d = %d\n\n", a, b, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void AddNumbers() {

        try {

            int a = inInt("First number:");
            int b = inInt("Second number:");

            int result = calculator.Add(a, b);

            System.out.format("%d + %d = %d\n\n", a, b, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }
}