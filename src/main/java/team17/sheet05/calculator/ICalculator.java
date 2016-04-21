package team17.sheet05.calculator;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote {
    int Add(int a, int b) throws RemoteException;

    int Subtract(int a, int b) throws RemoteException;

    int Multiply(int a, int b) throws RemoteException;

    int Lukas(int a) throws RemoteException;
}
