package team17.sheet05;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTask extends Remote {

    <T> T execute(Task<T> t) throws RemoteException;
}
