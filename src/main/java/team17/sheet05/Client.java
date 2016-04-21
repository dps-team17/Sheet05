package team17.sheet05;

import team17.sheet05.brain.DeepThoughtCallback;
import team17.sheet05.brain.IDeepThoughtCallback;
import team17.sheet05.brain.IDeepThoughtService;
import team17.sheet05.calculator.ICalculator;
import team17.sheet05.tasks.CalculateFibonacciTask;
import team17.sheet05.tasks.CalculatePiTask;
import team17.sheet05.helpers.KeyIn;
import team17.sheet05.tasks.RemoteTask;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static team17.sheet05.helpers.KeyIn.inInt;


public class Client {

    private static ICalculator calculator;
    private static RemoteTask taskRunner;
    private static Registry registry;

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            registry = LocateRegistry.getRegistry(host);
            calculator = (ICalculator) registry.lookup("ICalculator");
            taskRunner = (RemoteTask) registry.lookup("RemoteTask");

            run();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        System.out.println("Shutdown complete");
    }

    private static int getChoice() throws InterruptedException {

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
        System.out.println("| 7. Ask DeepThought      |");
        System.out.println("|                         |");
        System.out.println("| Settings:               |");
        System.out.println("| 9. Exit                 |");
        System.out.println("===========================");

        // Read choice
        return inInt(" Select option: ");
    }

    private static void run() {

        int choice = 0;

        do {
            try {

                // Skip, if error occurred in last iteration
                if(choice >= 0) choice = getChoice();

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
                            AskDeepThought();
                            break;
                        case 9:
                            System.out.println("Exit selected");
                            break;
                        case -3:
                            choice = OfferShutdown();
                            break;
                        default:
                            System.out.println("Invalid selection");
                    }
                }catch(InterruptedException e){
                    choice = -3;
                }

        } while (choice != 9);
    }

    private static int OfferShutdown() throws InterruptedException {

        int result = -3;

        String input = KeyIn.inString("Shutdown? (Y|N)");

            switch (input.toLowerCase()){
                case "y":
                    result = 9;
                    break;
                case "n":
                    result = 0;
                    break;
                default:
                    System.out.println("Invalid input");
            }

        return result;
    }

    private static void AskDeepThought() throws InterruptedException {
        try {
            String q = KeyIn.inString("Your question: ");

            IDeepThoughtCallback callback = new DeepThoughtCallback(System.in);
            IDeepThoughtCallback callbackStub = (IDeepThoughtCallback) UnicastRemoteObject.exportObject(callback, 0);

            IDeepThoughtService deepThoughtService = (IDeepThoughtService) registry.lookup("IDeepThoughtService");
            deepThoughtService.ThinkAbout(q, callbackStub);

            System.out.println("\n\n*** Your question is submitted to deep thought ***\nYou can continue your work while it's thinking about it.\n\n");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }


    }

    private static void FibonacciNumber() throws InterruptedException {
        try {
            int a = inInt("Number:");
            CalculateFibonacciTask task = new CalculateFibonacciTask(a);
            Integer fib = taskRunner.execute(task);

            System.out.printf("The fibonacci number %d is %d\n\n", a, fib);
        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void CalculatePi() throws InterruptedException {
        try {
            int digits = inInt("How may digits?:");
            CalculatePiTask task = new CalculatePiTask(digits);
            BigDecimal pi = taskRunner.execute(task);

            System.out.printf("Pi is %s\n\n", pi.toString());

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void LucasNumber() throws InterruptedException {
        try {

            int a = inInt("Number:");

            int result = calculator.Lukas(a);

            System.out.format("Lucas number %d = %d\n\n", a, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void MultiplyNumbers() throws InterruptedException {
        try {

            int a = inInt("First number:");
            int b = inInt("Second number:");

            int result = calculator.Multiply(a, b);

            System.out.format("%d * %d = %d\n\n", a, b, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void SubtractNumbers() throws InterruptedException {
        try {

            int a = inInt("First number:");
            int b = inInt("Second number:");

            int result = calculator.Subtract(a, b);

            System.out.format("%d - %d = %d\n\n", a, b, result);

        } catch (RemoteException e) {
            System.out.format("\nError during calculation:\n%s\n\n", e.getMessage());
        }
    }

    private static void AddNumbers() throws InterruptedException {

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