package team17.sheet05;

import java.rmi.Remote;

public interface IDeepThoughtService extends Remote {

    void ThinkAbout(String question, IDeepThoughtCallback callback);
}
