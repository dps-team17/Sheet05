package team17.sheet05.brain;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IDeepThoughtCallback extends Remote {

    void answer(String question, String answer, IDeepThoughtCallback callback) throws RemoteException;
}
