
package team17.sheet05;

import team17.sheet05.brain.DeepThoughtService;
import team17.sheet05.brain.IDeepThoughtService;
import team17.sheet05.calculator.Calculator;
import team17.sheet05.calculator.ICalculator;
import team17.sheet05.tasks.RemoteTask;
import team17.sheet05.tasks.RemoteTaskRunner;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {

        //Create Security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }


        try {
            // Create calculator stub
            ICalculator calculator = new Calculator();
            ICalculator calculatorStub = (ICalculator) UnicastRemoteObject.exportObject(calculator, 0);

            // Create task runner stub
            RemoteTask taskRunner = new RemoteTaskRunner();
            RemoteTask taskRunnerStub = (RemoteTask) UnicastRemoteObject.exportObject(taskRunner, 0);

            // Create deep thought service
            IDeepThoughtService deepThoughtService = new DeepThoughtService();
            IDeepThoughtService deepThoughtServiceStub = (IDeepThoughtService) UnicastRemoteObject.exportObject(deepThoughtService, 0);

            // Bind stubs in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ICalculator", calculatorStub);
            registry.rebind("RemoteTask", taskRunnerStub);
            registry.rebind("IDeepThoughtService", deepThoughtServiceStub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
