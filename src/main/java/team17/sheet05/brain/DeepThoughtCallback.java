package team17.sheet05.brain;


import java.io.InputStream;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

public class DeepThoughtCallback implements IDeepThoughtCallback {

    private final InputStream in;

    public DeepThoughtCallback(InputStream in) {
        super();

        this.in = in;
    }

    @Override
    public void answer(String question, String answer, IDeepThoughtCallback callback) {

        try {

            UnicastRemoteObject.unexportObject(this, true);
            System.out.printf("\n\n*** Result received from deep thought ***\nThe answer to your question \"%s\" is probably %s\n\n", question, answer);

        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }


    }
}
