
package team17.sheet05;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {

        try {
            ICalculator calculator = new Calculator();
            ICalculator stub1 = (ICalculator) UnicastRemoteObject.exportObject(calculator, 0);
            //Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("ICalculator", stub1);
            //registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
