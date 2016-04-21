package team17.sheet05.brain;


import java.io.InputStream;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

public class DeepThoughtCallback implements IDeepThoughtCallback {

    private final Thread mainThread;

    public DeepThoughtCallback(InputStream in) {
        super();

        this.mainThread = Thread.currentThread();
    }

    @Override
    public void answer(String question, String answer, IDeepThoughtCallback callback) {

        try {

            UnicastRemoteObject.unexportObject(this, true);
            System.out.printf("\n\n*** Result received from deep thought ***\nThe answer to your question \"%s\" is probably %s\n\n", question, answer);

            mainThread.interrupt();

        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
