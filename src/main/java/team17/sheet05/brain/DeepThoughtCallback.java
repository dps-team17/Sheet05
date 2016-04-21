package team17.sheet05.brain;


import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

public class DeepThoughtCallback implements IDeepThoughtCallback {

    private final Thread mainThread;

    public DeepThoughtCallback() {
        super();

        this.mainThread = Thread.currentThread();
    }

    @Override
    public void answer(String question, String answer) {

        try {

            UnicastRemoteObject.unexportObject(this, true);
            System.out.printf("\n\n*** Result received from deep thought ***\nThe answer to your question \"%s\" is probably %s\n\n", question, answer);

            mainThread.interrupt();

        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
