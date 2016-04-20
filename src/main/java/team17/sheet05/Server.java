
package team17.sheet05;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
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

            // Bind stubs in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ICalculator", calculatorStub);
            registry.rebind("RemoteTask", taskRunnerStub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
