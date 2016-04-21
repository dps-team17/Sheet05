package team17.sheet05.tasks;

import team17.sheet05.tasks.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTask extends Remote {

    <T> T execute(Task<T> t) throws RemoteException;
}
