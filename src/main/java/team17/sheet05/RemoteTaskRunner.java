package team17.sheet05;

import team17.sheet05.RemoteTask;
import team17.sheet05.Task;

import java.rmi.RemoteException;

public class RemoteTaskRunner implements RemoteTask {

    public RemoteTaskRunner(){
        super();
    }

    @Override
    public <T> T execute(Task<T> t) throws RemoteException {
        return t.execute();
    }
}
