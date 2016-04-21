package team17.sheet05.brain;

import java.rmi.RemoteException;

public class DeepThoughtService implements IDeepThoughtService {

    public DeepThoughtService() {
        super();
    }

    @Override
    public void ThinkAbout(String question, IDeepThoughtCallback callback) {

        new Thread() {

            public void run() {
                try {
                    Thread.sleep(5000);
                    callback.answer(question, String.valueOf(42), callback);


                } catch (InterruptedException | RemoteException e) {
                    e.printStackTrace();
                }

            }

        }.start();

    }
}
