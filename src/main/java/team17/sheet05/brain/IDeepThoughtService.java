package team17.sheet05.brain;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDeepThoughtService extends Remote {

    void ThinkAbout(String question, IDeepThoughtCallback callback)throws RemoteException;
}
