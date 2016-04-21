package team17.sheet05.tasks;

import team17.sheet05.tasks.RemoteTask;
import team17.sheet05.tasks.Task;

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
